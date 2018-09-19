package com.platform.service.impl;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.platform.common.SMSTypeEnum;
import com.platform.dao.SysSmsLogDao;
import com.platform.entity.SmsConfig;
import com.platform.entity.SysSmsLogEntity;
import com.platform.service.SysConfigService;
import com.platform.service.SysSmsLogService;
import com.platform.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * @author taylor
 */
@Service
public class SysSmsLogServiceImpl extends  BaseServiceImpl<SysSmsLogEntity,SysSmsLogDao> implements SysSmsLogService {


    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public SysSmsLogEntity sendSms(SysSmsLogEntity smsLog) {
        String result = "";
        //获取云存储配置信息
        SmsConfig config = sysConfigService.getConfigObject(Constant.SMS_CONFIG_KEY, SmsConfig.class);
        sentSms(smsLog, result, config);
        /**保存发送记录**/
        save(smsLog);
        return smsLog;
    }

    private void sentSms(SysSmsLogEntity smsLog, String result, SmsConfig config) {
        if (StringUtils.isNullOrEmpty(config)) {
            throw new RRException("请先配置短信平台信息");
        }
        if (SMSTypeEnum.CHUANGYUN.getCode() == config.getType()) {
            if (StringUtils.isNullOrEmpty(config.getName())) {
                throw new RRException("请先配置短信平台用户名");
            }
            if (StringUtils.isNullOrEmpty(config.getPwd())) {
                throw new RRException("请先配置短信平台密钥");
            }
            if (StringUtils.isNullOrEmpty(config.getChuangRuiSign())) {
                throw new RRException("请先配置短信平台签名");
            }
            try {
                /**
                 * 状态,发送编号,无效号码数,成功提交数,黑名单数和消息，无论发送的号码是多少，一个发送请求只返回一个sendid，如果响应的状态不是“0”，则只有状态和消息
                 */
                result = SmsUtil.crSendSms(config.getName(), config.getPwd(), smsLog.getMobile(), smsLog.getContent(), config.getChuangRuiSign(),
                        DateUtils.format(smsLog.getStime(), "yyyy-MM-dd HH:mm:ss"), smsLog.getExtno());
            } catch (Exception e) {

            }
            String arr[] = result.split(",");
            //发送成功
            if ("0".equals(arr[0])) {
                smsLog.setSendId(arr[1]);
                smsLog.setInvalidNum(Integer.parseInt(arr[2]));
                smsLog.setSuccessNum(Integer.parseInt(arr[3]));
                smsLog.setBlackNum(Integer.parseInt(arr[4]));
                smsLog.setReturnMsg(arr[5]);
            } else {
                //发送失败
                smsLog.setReturnMsg(arr[1]);
            }
            smsLog.setSendStatus(Integer.parseInt(arr[0]));
            try {
                smsLog.setUserId(ShiroUtils.getUserId());
            } catch (Exception e) {
                //外部发送短信
                smsLog.setUserId(1L);
            }
            smsLog.setSign(config.getChuangRuiSign());
            if (null == smsLog.getStime()) {
                smsLog.setStime(new Date());
            }

        }
        if (SMSTypeEnum.CHUANGYUN.getCode() == config.getType()) {
            if (StringUtils.isNullOrEmpty(config.getAppid())) {
                throw new RRException("请先配置腾讯云appId");
            }
            if (StringUtils.isNullOrEmpty(config.getAppkey())) {
                throw new RRException("请先配置腾讯云appKey");
            }
            if (StringUtils.isNullOrEmpty(config.getTemplateId())) {
                throw new RRException("请先配置腾讯云模板id");
            }
            if (StringUtils.isNullOrEmpty(config.getTengXunYunSign())) {
                throw new RRException("请先配置腾讯云签名");
            }
            try {
                String[] params = {"5678", "5"};
                SmsSingleSender ssender = new SmsSingleSender(config.getAppid(), config.getAppkey());
                // 签名参数未提供或者为空时，会使用默认签名发送短信
                SmsSingleSenderResult senderResult = ssender.sendWithParam("86", smsLog.getMobile(), config.getTemplateId(), params, config.getTengXunYunSign(), "", "");
                System.out.println(senderResult);
            } catch (HTTPException e) {
                // HTTP响应码错误
                e.printStackTrace();
            } catch (JSONException e) {
                // json解析错误
                e.printStackTrace();
            } catch (IOException e) {
                // 网络IO错误
                e.printStackTrace();
            }
        }
        //保存发送记录
    }

    public static void main(String[] args) {
        try {
            String[] params = {"123", "2"};
            SmsSingleSender ssender = new SmsSingleSender(1400127330, "3372cad45050d9c9f42fb5b4831309f6");
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            SmsSingleSenderResult senderResult = ssender.sendWithParam("86", "15921624157", 176827, params, "武汉创梦互联科技有限公司", "", "");
            System.out.println(senderResult);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }
}
