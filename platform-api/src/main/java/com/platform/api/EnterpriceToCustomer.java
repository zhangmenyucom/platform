package com.platform.api;
import com.platform.utils.wechat.Config;
import com.platform.entity.EnterpriceToCustomerEntity;
import com.platform.utils.wechat.ClientCustomSSL;
import com.platform.utils.wechat.RandCharsUtils;
import com.platform.utils.wechat.SignUtils;
import com.platform.utils.wechat.WXPayUtil;

import java.util.SortedMap;
import java.util.TreeMap;

import static com.platform.utils.wechat.EnterpricePayXmlToBeanUtils.parseXmlToMapEnterpriceToCustomer;


/**
 * 企业付款到用户的测试类
 * @author Taylor
 */
public class EnterpriceToCustomer {

    public void PayToCustom() {
        //1   拼凑企业支付需要的参数
        //微信公众号的appid
        String appid = Config.sub_appid;
        //商户号
        String mch_id = Config.mch_id;
        //生成随机数
        String nonce_str = RandCharsUtils.getRandomString(32);
        //生成商户订单号
        String partner_trade_no = RandCharsUtils.getRandomString(32);
        // 支付给用户openid
        String openid = Config.openid;
        //是否验证真实姓名
        String check_name = "FORCE_CHECK";
        //收款用户姓名
        String re_user_name = "张晓路";
        //企业付款金额，单位为分
        String amount = "100";
        //企业付款操作说明信息。必填。
        String desc = "测试企业付款到零钱";
        String spbill_create_ip = "192.168.1.250";

        //2   生成map集合
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        //微信公众号的appid
        packageParams.put("mch_appid", appid);
        //商务号
        packageParams.put("mchid", mch_id);
        //随机生成后数字，保证安全性
        packageParams.put("nonce_str", nonce_str);
        //生成商户订单号
        packageParams.put("partner_trade_no", partner_trade_no);
        // 支付给用户openid
        packageParams.put("openid", openid);
        //是否验证真实姓名呢
        packageParams.put("check_name", check_name);
        //收款用户姓名
        packageParams.put("re_user_name", re_user_name);
        //企业付款金额，单位为分
        packageParams.put("amount", 30);
        //企业付款操作说明信息。必填。
        packageParams.put("desc", desc);
        //调用接口的机器Ip地址
        packageParams.put("spbill_create_ip", spbill_create_ip);

        //3   生成自己的签名
        String sign = SignUtils.creatSign("utf-8", packageParams);

        //4   封装退款对象
        packageParams.put("sign", sign);

        //5  将当前的map结合转化成xml格式
        String reuqestXml = WXPayUtil.getRequestXml(packageParams);
        System.out.println("请求xml---：");
        System.out.println(reuqestXml);
        //6  获取需要发送的url地址
        //获取退款的api接口
        String wxUrl = Config.enterprice_url;
        try {
            String weixinPost = ClientCustomSSL.doRefund(wxUrl, Config.cert_path, Config.mch_id, reuqestXml).toString();
            //7   解析返回的xml数据
            EnterpriceToCustomerEntity etoc = parseXmlToMapEnterpriceToCustomer(weixinPost);
            if ("SUCCESS".equalsIgnoreCase(etoc.getResult_code()) && "SUCCESS".equalsIgnoreCase(etoc.getReturn_code())) {
                //8表示退款成功
                //TODO 执行成功付款后的业务逻辑
                //return successPayMoneyToCustom(submitMoney,cmms_amt,enterpriceToCustomerByCard,applyId,companyId);
            } else {
                //9 表示退款失败
                //TODO 调用service的方法 ，存储失败提现的记录咯
                //failToPayMoneyToCustom(enterpriceToCustomerByCard,applyId);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EnterpriceToCustomer e = new EnterpriceToCustomer();
        e.PayToCustom();
    }

}




		