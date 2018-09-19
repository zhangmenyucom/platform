package com.platform.api;

import com.alibaba.fastjson.JSON;
import com.platform.annotation.IgnoreAuth;
import com.platform.util.ApiBaseAction;
import com.platform.util.wechat.*;
import com.platform.util.wechat.pay.JsonResult;
import com.platform.util.wechat.pay.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.platform.config.Constants.*;

/**
 * 创建时间：2016年11月9日 下午5:49:00
 *
 * @author andy
 * @version 2.2
 */
@Slf4j
@RestController
@RequestMapping("/api/transfer")
public class ApiTransferController extends ApiBaseAction {
    /**
     * 企业向个人支付转账
     *
     * @param request
     * @param response
     * @param openId
     * @param callback
     */
    @IgnoreAuth
    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public void transferPay(HttpServletRequest request, HttpServletResponse response, @RequestParam("openId") String openId, String callback) {
        log.info("[/transfer/pay]");
        //业务判断 openid是否有收款资格

        Map<String, String> restmap = null;
        try {
            Map<String, String> parm = new TreeMap<>();
            //公众账号appid
            parm.put("mch_appid", APP_ID);
            //商户号
            parm.put("mchid", MCH_ID);
            //随机字符串
            parm.put("nonce_str", PayUtil.getNonceStr());
            //商户订单号
            parm.put("partner_trade_no", PayUtil.getTransferNo());
            //用户openid
            parm.put("openid", openId);
            //校验用户姓名选项 OPTION_CHECK
            parm.put("check_name", "NO_CHECK");
            //check_name设置为FORCE_CHECK或OPTION_CHECK，则必填
            //parm.put("re_user_name", "安迪");
            //转账金额
            parm.put("amount","2");
            //企业付款描述信息
            parm.put("desc", "测试转账到个人");
            //Ip地址
            parm.put("spbill_create_ip", PayUtil.getLocalIp(request));
            parm.put("sign", PayUtil.getSign(parm, API_SECRET));
            String restxml = HttpUtils.posts(TRANSFERS_PAY, XmlUtil.xmlFormat(parm, false));
            restmap = XmlUtil.xmlParse2(restxml);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        afterTransfersProcess(response, callback, restmap);
    }

    /**
     * 企业向个人转账查询
     *
     * @param request
     * @param response
     * @param tradeno  商户转账订单号
     * @param callback
     */
    @IgnoreAuth
    @RequestMapping(value = "/pay/query", method = RequestMethod.POST)
    public void orderPayQuery(HttpServletRequest request, HttpServletResponse response, String tradeno, String callback) {
        log.info("[/transfer/pay/query]");
        if (StringUtil.isEmpty(tradeno)) {
            WebUtil.response(response, WebUtil.packJsonp(callback, JSON.toJSONString(new JsonResult(-1, "转账订单号不能为空", new ResponseData()), SerializerFeatureUtil.FEATURES)));
        }

        Map<String, String> restmap = null;
        try {
            Map<String, String> parm = new HashMap<>(8);
            parm.put("appid", APP_ID);
            parm.put("mch_id", MCH_ID);
            parm.put("partner_trade_no", tradeno);
            parm.put("nonce_str", PayUtil.getNonceStr());
            parm.put("sign", PayUtil.getSign(parm, API_SECRET));

            String restxml = HttpUtils.posts(TRANSFERS_PAY_QUERY, XmlUtil.xmlFormat(parm, true));
            restmap = XmlUtil.xmlParse(restxml);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        afterTransfersProcess(response, callback, restmap);
    }

    private void afterTransfersProcess(HttpServletResponse response, String callback, Map<String, String> restmap) {
        if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {
            // 订单查询成功 处理业务逻辑
            log.info("订单查询：订单" + restmap.get("partner_trade_no") + "支付成功");
            Map<String, String> transferMap = new HashMap<>(8);
            //商户转账订单号
            transferMap.put("partner_trade_no", restmap.get("partner_trade_no"));
            //收款微信号
            transferMap.put("openid", restmap.get("openid"));
            //转账金额
            transferMap.put("payment_amount", restmap.get("payment_amount"));
            //转账时间
            transferMap.put("transfer_time", restmap.get("transfer_time"));
            //转账描述
            transferMap.put("desc", restmap.get("desc"));
            WebUtil.response(response, WebUtil.packJsonp(callback, JSON.toJSONString(new JsonResult(1, "订单转账成功", new ResponseData(null, transferMap)), SerializerFeatureUtil.FEATURES)));
        } else {
            if (CollectionUtil.isNotEmpty(restmap)) {
                log.info("订单转账失败：" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
            }
            WebUtil.response(response, WebUtil.packJsonp(callback, JSON.toJSONString(new JsonResult(-1, "订单转账失败", new ResponseData()), SerializerFeatureUtil.FEATURES)));
        }
    }

}
