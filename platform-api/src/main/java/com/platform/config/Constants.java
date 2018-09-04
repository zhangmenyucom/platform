package com.platform.config;

import com.platform.utils.ResourceUtil;

/**
 * @author xiaolu.zhang
 * @desc:常量类
 * @date: 2018/9/4 23:24
 */
public class Constants {
    // 企业付款
    public  static final String TRANSFERS_PAY = ResourceUtil.getConfigByName("wx.transfers_pay");
    // 企业付款查询
    public  static final String TRANSFERS_PAY_QUERY =ResourceUtil.getConfigByName("wx.transfers_pay_query");

    public  static final String APP_ID = ResourceUtil.getConfigByName("wx.appId");

    public static final String MCH_ID = ResourceUtil.getConfigByName("wx.mchId");

    public static final String API_SECRET =ResourceUtil.getConfigByName("wx.secret");
}
