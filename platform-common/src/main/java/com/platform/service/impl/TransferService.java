package com.platform.service.impl;/**
 * ${author} on 2018/10/8.
 */

import com.platform.dao.SysUserConfigDao;
import com.platform.entity.EnterpriceToCustomerEntity;
import com.platform.entity.SysUserConfigEntity;
import com.platform.entity.TransferReqBean;
import com.platform.utils.wechat.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.SortedMap;
import java.util.TreeMap;


/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/10/8 18:26
 */
@Service
public class TransferService {

    @Autowired
    private SysUserConfigDao sysUserConfigDao;

    public void PayToCustom(TransferReqBean transferReqBean) {
        SysUserConfigEntity sysUserConfigVo = sysUserConfigDao.queryByMerchantId(transferReqBean.getMerchantId());
        String spbill_create_ip = "192.168.1.250";

        SortedMap<Object, Object> packageParams = new TreeMap<>();
        //微信公众号的appid
        packageParams.put("mch_appid", sysUserConfigVo.getAppId());
        //商务号
        packageParams.put("mchid", sysUserConfigVo.getMchId());
        //随机生成后数字，保证安全性
        packageParams.put("nonce_str", RandCharsUtils.getRandomString(32));
        //生成商户订单号
        packageParams.put("partner_trade_no", RandCharsUtils.getRandomString(32));
        // 支付给用户openid
        packageParams.put("openid", transferReqBean.getOpenId());
        //是否验证真实姓名呢
        packageParams.put("check_name", "NO_CHECK");
        //收款用户姓名
        packageParams.put("re_user_name", transferReqBean.getRealName());
        //企业付款金额，单位为分
        packageParams.put("amount", transferReqBean.getAmount());
        //企业付款操作说明信息。必填。
        packageParams.put("desc", "测试企业付款到零钱");
        //调用接口的机器Ip地址
        packageParams.put("spbill_create_ip", spbill_create_ip);

        //3   生成自己的签名
        String sign = SignUtils.creatSign("utf-8", packageParams);

        //4   封装退款对象
        packageParams.put("sign", sign);

        //5  将当前的map结合转化成xml格式
        String reuqestXml = WXPayUtil.getRequestXml(packageParams);
        //6  获取需要发送的url地址
        //获取退款的api接口
        String wxUrl = Config.enterprice_url;
        try {
            String weixinPost = ClientCustomSSL.doRefund(wxUrl, reuqestXml).toString();
            EnterpriceToCustomerEntity etoc = EnterpricePayXmlToBeanUtils.parseXmlToMapEnterpriceToCustomer(weixinPost);
            if ("SUCCESS".equalsIgnoreCase(etoc.getResult_code())) {
                //TODO 执行成功付款后的业务逻辑
            } else {
                //TODO 调用service的方法 ，存储失败提现的记录咯
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
