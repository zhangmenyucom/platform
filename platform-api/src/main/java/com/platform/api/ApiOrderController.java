package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.common.OrderStatusEnum;
import com.platform.common.PayStatusEnum;
import com.platform.common.ShippingStatusEnum;
import com.platform.entity.OrderGoodsVo;
import com.platform.entity.OrderVo;
import com.platform.entity.UserVo;
import com.platform.service.ApiKdniaoService;
import com.platform.service.ApiOrderGoodsService;
import com.platform.service.ApiOrderService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.util.wechat.WechatUtil;
import com.platform.util.wechat.WeichatRefundApiResult;
import com.platform.utils.HttpApiClient;
import com.platform.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "订单相关")
@RestController
@RequestMapping("/api/order")
public class ApiOrderController extends ApiBaseAction {
    @Autowired
    private ApiOrderService orderService;
    @Autowired
    private ApiOrderGoodsService orderGoodsService;

    /**
     */
    @ApiOperation(value = "订单首页")
    @IgnoreAuth
    @GetMapping("index")
    public Object index() {
        return toResponsSuccess("");
    }

    /**
     * 获取订单列表
     */
    @ApiOperation(value = "获取订单列表")
    @GetMapping("list")
    public Object list(@LoginUser UserVo loginUser, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map params = new HashMap(0);
        params.put("user_id", loginUser.getUserId());
        params.put("page", page);
        params.put("limit", size);
        params.put("sidx", "id");
        params.put("order", "asc");
        /**查询列表数据**/
        Query query = new Query(params);
        List<OrderVo> orderEntityList = orderService.queryList(query);
        int total = orderService.queryTotal(query);
        ApiPageUtils pageUtil = new ApiPageUtils(orderEntityList, total, query.getLimit(), query.getPage());
        for (OrderVo item : orderEntityList) {
            Map orderGoodsParam = new HashMap(1);
            orderGoodsParam.put("order_id", item.getId());
            /**订单的商品**/
            List<OrderGoodsVo> goodsList = orderGoodsService.queryList(orderGoodsParam);
            Integer goodsCount = 0;
            for (OrderGoodsVo orderGoodsEntity : goodsList) {
                goodsCount += orderGoodsEntity.getNumber();
                item.setGoodsCount(goodsCount);
            }
        }
        return toResponsSuccess(pageUtil);
    }

    /**
     * 获取订单详情
     */
    @ApiOperation(value = "获取订单详情")
    @GetMapping("detail")
    public Object detail(Integer orderId) {
        Map resultObj = new HashMap(0);
        /****/
        OrderVo orderInfo = orderService.queryObject(orderId);
        if (null == orderInfo) {
            return toResponsObject(400, "订单不存在", "");
        }
        Map orderGoodsParam = new HashMap(0);
        orderGoodsParam.put("order_id", orderId);
        /**订单的商品**/
        List<OrderGoodsVo> orderGoods = orderGoodsService.queryList(orderGoodsParam);
        /**订单最后支付时间**/
        if (orderInfo.getOrder_status() == 0) {

        }
        /**订单可操作的选择,删除，支付，收货，评论，退换货**/
        Map handleOption = orderInfo.getHandleOption();
        /****/
        resultObj.put("orderInfo", orderInfo);
        resultObj.put("orderGoods", orderGoods);
        resultObj.put("handleOption", handleOption);
        if (!StringUtils.isEmpty(orderInfo.getShipping_no())) {
            resultObj.put("shippingList", HttpApiClient.getMailInfo(orderInfo.getShipping_no()).getData());
        }
        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "修改订单")
    @PostMapping("updateSuccess")
    public Object updateSuccess(Integer orderId) {
        OrderVo orderInfo = orderService.queryObject(orderId);
        if (orderInfo == null) {
            return toResponsFail("订单不存在");
        } else if (orderInfo.getOrder_status() != 0) {
            return toResponsFail("订单状态不正确orderStatus" + orderInfo.getOrder_status() + "payStatus" + orderInfo.getPay_status());
        }

        orderInfo.setId(orderId);
        orderInfo.setPay_status(PayStatusEnum.PAYED.getCode());
        orderInfo.setOrder_status(OrderStatusEnum.WAITTING_SHIP.getCode());
        orderInfo.setShipping_status(ShippingStatusEnum.NOT_SHIPPED.getCode());
        orderInfo.setPay_time(new Date());
        int num = orderService.update(orderInfo);
        if (num > 0) {
            return toResponsMsgSuccess("修改订单成功");
        } else {
            return toResponsFail("修改订单失败");
        }
    }

    /**
     * 订单提交
     */
    @ApiOperation(value = "订单提交")
    @PostMapping("submit")
    public Object submit(@LoginUser UserVo loginUser) {
        Map resultObj = null;
        try {
            resultObj = orderService.submit(getJsonRequest(), loginUser);
            if (null != resultObj) {
                return toResponsObject(MapUtils.getInteger(resultObj, "errno"), MapUtils.getString(resultObj, "errmsg"), resultObj.get("data"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("提交失败");
    }

    /**
     * 获取订单列表
     */
    @ApiOperation(value = "取消订单")
    @PostMapping("cancelOrder")
    public Object cancelOrder(Integer orderId) {
        try {
            OrderVo orderVo = orderService.queryObject(orderId);
            if (Objects.equals(orderVo.getOrder_status(), OrderStatusEnum.SHIPPED.getCode())) {
                return toResponsFail("已发货，不能取消");
            } else if (Objects.equals(orderVo.getOrder_status(), OrderStatusEnum.CONFIRMED.getCode())) {
                return toResponsFail("已收货，不能取消");
            }
            /** 需要退款**/
            if (Objects.equals(orderVo.getPay_status(), PayStatusEnum.PAYED.getCode())) {
                WeichatRefundApiResult result = WechatUtil.wxRefund(orderVo.getId().toString(), 0.01, 0.01);
                if ("SUCCESS".equals(result.getResult_code())) {
                    if (Objects.equals(orderVo.getOrder_status(), OrderStatusEnum.WAITTING_SHIP.getCode())) {
                        orderVo.setOrder_status(OrderStatusEnum.REFUND_WITHOUT_SHIP.getCode());
                    } else if (Objects.equals(orderVo.getOrder_status(), OrderStatusEnum.SHIPPED.getCode())) {
                        orderVo.setOrder_status(OrderStatusEnum.REFUND_WITH_GOODS_RETURNED.getCode());
                    }
                    orderVo.setPay_status(PayStatusEnum.REFUND.getCode());
                    orderService.update(orderVo);
                    return toResponsMsgSuccess("取消成功");
                } else {
                    return toResponsObject(400, "取消成失败", "");
                }
            } else {
                orderVo.setOrder_status(OrderStatusEnum.CANCEL.getCode());
                orderService.update(orderVo);
                return toResponsSuccess("取消成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("提交失败");
    }

    /**
     * 确认收货
     */
    @ApiOperation(value = "确认收货")
    @PostMapping("confirmOrder")
    public Object confirmOrder(Integer orderId) {
        try {
            OrderVo orderVo = orderService.queryObject(orderId);
            orderVo.setOrder_status(OrderStatusEnum.CONFIRMED.getCode());
            orderVo.setShipping_status(ShippingStatusEnum.CONFIRMED.getCode());
            orderVo.setConfirm_time(new Date());
            orderService.update(orderVo);
            return toResponsSuccess("确认收货成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("提交失败");
    }
}