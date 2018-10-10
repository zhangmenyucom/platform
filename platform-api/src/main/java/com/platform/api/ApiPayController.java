package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.cache.J2CacheUtils;
import com.platform.common.OrderStatusEnum;
import com.platform.common.PayStatusEnum;
import com.platform.common.ShippingStatusEnum;
import com.platform.config.CommissionRule;
import com.platform.entity.*;
import com.platform.enums.UserLevelEnum;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.platform.utils.wechat.WechatUtil;
import com.platform.utils.wechat.WeichatRefundApiResult;
import com.platform.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.platform.enums.SpecialGoodsEnum.SPECIAL_GOODS_ENUM_MAP;
import static com.platform.enums.UserLevelEnum.LEVEL_MAP;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "商户支付")
@RestController
@RequestMapping("/api/{merchantId}/pay")
@Slf4j
public class ApiPayController extends ApiBaseAction {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private ApiOrderService orderService;
    @Autowired
    private ApiOrderGoodsService orderGoodsService;
    @Autowired
    private ApiUserService userService;
    @Autowired
    private ApiCommissionOrderService commissionOrderService;
    @Autowired
    private CommissionRule commissionRule;
    @Autowired
    private ApiUserConfigService apiUserConfigService;


    /**
     */
    @ApiOperation(value = "跳转")
    @GetMapping("index")
    public Object index() {
        //
        return toResponsSuccess("");
    }

    /**
     * 获取支付的请求参数
     */
    @ApiOperation(value = "获取支付的请求参数")
    @GetMapping("prepay")
    public Object payPrepay(@PathVariable("merchantId") Long merchantId, @LoginUser UserVo loginUser, Long orderId) {
        //
        OrderVo orderInfo = orderService.queryObject(orderId);

        if (null == orderInfo) {
            return toResponsObject(400, "订单已取消", "");
        }

        if (Objects.equals(orderInfo.getPay_status(), PayStatusEnum.PAYED.getCode())) {
            return toResponsObject(400, "订单已支付，请不要重复操作", "");
        }
        if (Objects.equals(orderInfo.getPay_status(), PayStatusEnum.REFUND.getCode())) {
            return toResponsObject(400, "订单已退款", "");
        }
        SysUserConfigVo sysUserConfigVo = apiUserConfigService.queryByMerchantId(merchantId);
        String nonceStr = CharUtil.getRandomString(32);

        Map<Object, Object> resultObj = new TreeMap();

        try {
            Map<Object, Object> parameters = new TreeMap<>();
            parameters.put("appid", sysUserConfigVo.getAppId());
            // 商家账号。
            parameters.put("mch_id", sysUserConfigVo.getMchId());
            String randomStr = CharUtil.getRandomNum(18).toUpperCase();
            // 随机字符串
            parameters.put("nonce_str", randomStr);
            // 商户订单编号
            parameters.put("out_trade_no", orderId);
            Map orderGoodsParam = new HashMap(0);
            orderGoodsParam.put("order_id", orderId);
            // 商品描述
            parameters.put("body", "商城-支付");
            //订单的商品
            List<OrderGoodsVo> orderGoods = orderGoodsService.queryList(orderGoodsParam, merchantId);
            if (null != orderGoods) {
                String body = "商城-";
                for (OrderGoodsVo goodsVo : orderGoods) {
                    body = body + goodsVo.getGoods_name() + "、";
                }
                if (body.length() > 0) {
                    body = body.substring(0, body.length() - 1);
                }
                // 商品描述
                parameters.put("body", body);
            }
            //支付金额

            parameters.put("total_fee", orderInfo.getActual_price().multiply(new BigDecimal(100)).intValue());
            // 回调地址
            parameters.put("notify_url", String.format(ResourceUtil.getConfigByName("wx.notifyUrl"), merchantId));

            // 交易类型APP
            parameters.put("trade_type", ResourceUtil.getConfigByName("wx.tradeType"));
            parameters.put("spbill_create_ip", getClientIp());
            parameters.put("openid", loginUser.getWeixin_openid());
            String sign = WechatUtil.arraySign(parameters, sysUserConfigVo.getPaySignKey());
            // 数字签证
            parameters.put("sign", sign);

            String xml = MapUtils.convertMap2Xml(parameters);
            logger.info("xml:" + xml);
            Map<String, Object> resultUn = XmlUtil.xmlStrToMap(WechatUtil.requestOnce(ResourceUtil.getConfigByName("wx.uniformorder"), xml));
            // 响应报文
            String return_code = MapUtils.getString("return_code", resultUn);
            String return_msg = MapUtils.getString("return_msg", resultUn);
            //
            if (return_code.equalsIgnoreCase("FAIL")) {
                return toResponsFail("支付失败," + return_msg);
            } else if (return_code.equalsIgnoreCase("SUCCESS")) {
                // 返回数据
                String result_code = MapUtils.getString("result_code", resultUn);
                String err_code_des = MapUtils.getString("err_code_des", resultUn);
                if (result_code.equalsIgnoreCase("FAIL")) {
                    return toResponsFail("支付失败," + err_code_des);
                } else if (result_code.equalsIgnoreCase("SUCCESS")) {
                    String prepay_id = MapUtils.getString("prepay_id", resultUn);
                    // 先生成paySign 参考https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=7_7&index=5
                    resultObj.put("appId", sysUserConfigVo.getAppId());
                    resultObj.put("timeStamp", DateUtils.timeToStr(System.currentTimeMillis() / 1000, DateUtils.DATE_TIME_PATTERN));
                    resultObj.put("nonceStr", nonceStr);
                    resultObj.put("package", "prepay_id=" + prepay_id);
                    resultObj.put("signType", "MD5");
                    String paySign = WechatUtil.arraySign(resultObj, sysUserConfigVo.getPaySignKey());
                    resultObj.put("paySign",paySign);
                    // 业务处理
                    orderInfo.setPay_id(prepay_id);
                    // 付款中
                    orderInfo.setPay_status(1);
                    orderService.update(orderInfo);
                    return toResponsObject(0, "微信统一订单下单成功", resultObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return toResponsFail("下单失败,error=" + e.getMessage());
        }
        return toResponsFail("下单失败");
    }

    /**
     * 微信查询订单状态
     */
    @ApiOperation(value = "查询订单状态")
    @GetMapping("query")
    public Object orderQuery(@PathVariable("merchantId") Long merchantId, @LoginUser UserVo loginUser, Long orderId) {
        if (orderId == null) {
            return toResponsFail("订单不存在");
        }
        SysUserConfigVo sysUserConfigVo = apiUserConfigService.queryByMerchantId(merchantId);
        Map<Object, Object> parame = new TreeMap<>();
        parame.put("appid", sysUserConfigVo.getAppId());
        // 商家账号。
        parame.put("mch_id", sysUserConfigVo.getMchId());
        String randomStr = CharUtil.getRandomNum(18).toUpperCase();
        // 随机字符串
        parame.put("nonce_str", randomStr);
        // 商户订单编号
        parame.put("out_trade_no", orderId);

        String sign = WechatUtil.arraySign(parame, sysUserConfigVo.getPaySignKey());
        // 数字签证
        parame.put("sign", sign);

        String xml = MapUtils.convertMap2Xml(parame);
        logger.info("xml:" + xml);
        Map<String, Object> resultUn = null;
        try {
            resultUn = XmlUtil.xmlStrToMap(WechatUtil.requestOnce(ResourceUtil.getConfigByName("wx.orderquery"), xml));
        } catch (Exception e) {
            e.printStackTrace();
            return toResponsFail("查询失败,error=" + e.getMessage());
        }
        // 响应报文
        String return_code = MapUtils.getString("return_code", resultUn);
        String return_msg = MapUtils.getString("return_msg", resultUn);

        if (return_code.equals("SUCCESS")) {
            String trade_state = MapUtils.getString("trade_state", resultUn);
            if (trade_state.equals("SUCCESS")) {
                // 更改订单状态
                // 业务处理
                OrderVo orderInfo = new OrderVo();
                orderInfo.setId(orderId);
                orderInfo.setPay_status(PayStatusEnum.PAYED.getCode());
                orderInfo.setOrder_status(OrderStatusEnum.WAITTING_SHIP.getCode());
                orderInfo.setShipping_status(ShippingStatusEnum.NOT_SHIPPED.getCode());
                orderInfo.setPay_time(new Date());
                orderService.update(orderInfo);
                return toResponsMsgSuccess("支付成功");
            } else if (trade_state.equals("USERPAYING")) {
                // 重新查询 正在支付中
                Integer num = (Integer) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME, "queryRepeatNum" + orderId + "");
                if (num == null) {
                    J2CacheUtils.put(J2CacheUtils.SHOP_CACHE_NAME, "queryRepeatNum" + orderId + "", 1);
                    this.orderQuery(merchantId,loginUser, orderId);
                } else if (num <= 3) {
                    J2CacheUtils.remove(J2CacheUtils.SHOP_CACHE_NAME, "queryRepeatNum" + orderId);
                    this.orderQuery(merchantId,loginUser, orderId);
                } else {
                    return toResponsFail("查询失败,error=" + trade_state);
                }

            } else {
                // 失败
                return toResponsFail("查询失败,error=" + trade_state);
            }
        } else {
            return toResponsFail("查询失败,error=" + return_msg);
        }
        return toResponsFail("查询失败，未知错误");
    }

    /**
     * 微信订单回调接口
     *
     * @return
     */
    @IgnoreAuth
    @ApiOperation(value = "微信订单回调接口")
    @RequestMapping(value = "/notify", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public synchronized void notify(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            InputStream in = request.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
            //xml数据
            String reponseXml = new String(out.toByteArray(), "utf-8");

            WeichatRefundApiResult result = (WeichatRefundApiResult) XmlUtil.xmlStrToBean(reponseXml, WeichatRefundApiResult.class);
            String result_code = result.getResult_code();
            if (result_code.equalsIgnoreCase("FAIL")) {
                //订单编号
                String out_trade_no = result.getOut_trade_no();
                logger.error("订单" + out_trade_no + "支付失败");
                response.getWriter().write(setXml("SUCCESS", "OK"));
            } else if (result_code.equalsIgnoreCase("SUCCESS")) {
                //订单编号
                String out_trade_no = result.getOut_trade_no();
                logger.error("订单" + out_trade_no + "支付成功");
                // 业务处理
                OrderVo orderInfo = orderService.queryObject(Long.valueOf(out_trade_no));

                /**更改订单状态**/
                orderInfo.setPay_status(PayStatusEnum.PAYED.getCode());
                orderInfo.setOrder_status(OrderStatusEnum.WAITTING_SHIP.getCode());
                orderInfo.setShipping_status(ShippingStatusEnum.NOT_SHIPPED.getCode());
                orderInfo.setPay_time(new Date());

                /**推荐反佣金(3级)**/
                UserVo userSource = userService.queryObject(orderInfo.getUser_id());
                Map<String, Object> queryMap = new HashMap<>(1);
                queryMap.put("order_id", orderInfo.getId());
                List<Long> orderGoodsIdsList = orderGoodsService.queryList(queryMap).stream().map(OrderGoodsVo::getGoods_id).collect(Collectors.toList());
                for (Long goodsId : orderGoodsIdsList) {
                    if (SPECIAL_GOODS_ENUM_MAP.get(goodsId) != null) {
                        /**购买角色，更新用户的角色**/
                        UserVo userVo = new UserVo();
                        userVo.setUserId(orderInfo.getUser_id());
                        if (orderInfo.getParent_id() != null) {
                            userVo.setParentId(Long.valueOf(orderInfo.getParent_id()));
                            userSource.setParentId(Long.valueOf(orderInfo.getParent_id()));
                        }
                        userVo.setUser_level_id(SPECIAL_GOODS_ENUM_MAP.get(goodsId).getRoleId());
                        userService.update(userVo);

                        UserVo userParent = null;
                        UserVo userGrandFater = null;
                        if (userSource.getParentId() != null) {
                            userParent = userService.queryObject(userSource.getParentId());
                            if (userParent.getParentId() != null) {
                                userGrandFater = userService.queryObject(userParent.getParentId());
                            }
                        }
                        if (userParent != null) {
                            /**第一级提成**/
                            CommissionOrderVo commissionFirst = commissionRule.getCommition(userParent, orderInfo, 1);
                            addToBalance(userParent, commissionFirst);
                            /**第二级提成**/
                            if (LEVEL_MAP.get(userSource.getUser_level_id()) != UserLevelEnum.HEHUOREN && LEVEL_MAP.get(userParent.getUser_level_id()) != UserLevelEnum.HEHUOREN && userGrandFater != null) {
                                CommissionOrderVo commissionSecond = commissionRule.getCommition(userGrandFater, orderInfo, 2);
                                addToBalance(userGrandFater, commissionSecond);
                            }
                        }
                        /**特殊定单，直接完成发货**/
                        orderInfo.setOrder_status(OrderStatusEnum.SHIPPED.getCode());
                    }
                }
                orderService.update(orderInfo);
                response.getWriter().write(setXml("SUCCESS", "OK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * 给主账户修改佣金并记录佣金明细
     **/
    private void addToBalance(UserVo user, CommissionOrderVo commission) {
        if (commission != null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("userId", commission.getUserId());
            map.put("orderSn", commission.getOrderSn());
            if (commissionOrderService.queryList(map).isEmpty()) {
                UserVo grandP = new UserVo();
                grandP.setUserId(user.getUserId());
                grandP.setTotalBalance(user.getTotalBalance().add(new BigDecimal("" + commission.getGainBalance())));
                grandP.setAvilableBalance(user.getAvilableBalance().add(new BigDecimal("" + commission.getGainBalance())));
                log.info("grantP" + JsonUtil.getJsonByObj(grandP));
                userService.update(grandP);
                commissionOrderService.save(commission);
            }
        }
    }

    /**
     * 订单退款请求
     */
    @ApiOperation(value = "订单退款请求")
    @PostMapping("refund")
    public Object refund(Long orderId) {
        //
        OrderVo orderInfo = orderService.queryObject(orderId);

        if (null == orderInfo) {
            return toResponsObject(400, "订单已取消", "");
        }

        if (Objects.equals(orderInfo.getOrder_status(), OrderStatusEnum.REFUND_WITHOUT_SHIP.getCode()) || Objects.equals(orderInfo.getOrder_status(), OrderStatusEnum.REFUND_WITH_GOODS_RETURNED.getCode())) {
            return toResponsObject(400, "订单已退款", "");
        }

        WeichatRefundApiResult result = WechatUtil.wxRefund(orderInfo.getId().toString(), 10.01, 10.01);
        if (result.getResult_code().equals("SUCCESS")) {
            if (Objects.equals(orderInfo.getOrder_status(), OrderStatusEnum.WAITTING_SHIP.getCode())) {
                orderInfo.setOrder_status(OrderStatusEnum.REFUND_WITHOUT_SHIP.getCode());
            } else if (Objects.equals(orderInfo.getOrder_status(), OrderStatusEnum.SHIPPED.getCode())) {
                orderInfo.setOrder_status(OrderStatusEnum.REFUND_WITH_GOODS_RETURNED.getCode());
            }
            orderService.update(orderInfo);
            return toResponsObject(400, "成功退款", "");
        } else {
            return toResponsObject(400, "退款失败", "");
        }
    }


    //返回微信服务
    public static String setXml(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }

    //模拟微信回调接口
    public static String callbakcXml(String orderNum) {
        return "<xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CFT]]></bank_type><fee_type><![CDATA[CNY]]></fee_type> <is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid> <out_trade_no><![CDATA[" + orderNum + "]]></out_trade_no>  <result_code><![CDATA[SUCCESS]]></result_code> <return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign><sub_mch_id><![CDATA[10000100]]></sub_mch_id> <time_end><![CDATA[20140903131540]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id></xml>";
    }
}