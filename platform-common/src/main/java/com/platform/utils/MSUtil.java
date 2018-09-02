package com.platform.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/9/2 1:24
 */
public class MSUtil {
    private static final Integer appId = 1400127330;
    private static final Integer templateId = 176827;
    private static final String prefix = "86";
    private static final String sign = "武汉创梦互联科技有限公司";
    private static final String appKey = "3372cad45050d9c9f42fb5b4831309f6";
    private static final SmsSingleSender sender = new SmsSingleSender(appId, appKey);

    public static SmsSingleSenderResult sendMessage(String phone, String[] params) throws Exception {
        return sender.sendWithParam(prefix, phone, templateId, params, sign, "", "");
    }
}
