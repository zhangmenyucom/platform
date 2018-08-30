package com.platform.utils;/**
 * ${author} on 2018/8/30.
 */

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.platform.entity.MailCompanyResponse;
import com.platform.entity.MailInfoEntity;
import retrofit2.Retrofit;

import java.io.IOException;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/30 8:55
 */
public class HttpApiClient {
    public static final Retrofit retrofitMail = new Retrofit.Builder().addConverterFactory(new Retrofit2ConverterFactory())
            .baseUrl("http://www.kuaidi100.com").build();
    public static HttpApi apiMail = retrofitMail.create(HttpApi.class);

    public static MailCompanyResponse getMailPosibleCompanyInfo(String mailNo) {
        try {
            return apiMail.getMailCompanyInfo(mailNo).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MailInfoEntity getMailInfo(String mailNo) {
        try {
            MailCompanyResponse mailPosibleCompanyInfo = getMailPosibleCompanyInfo(mailNo);
            if (mailPosibleCompanyInfo == null || mailPosibleCompanyInfo.getAuto().isEmpty()) {
                return null;
            }
            return apiMail.getMailInfo(mailPosibleCompanyInfo.getAuto().get(0).getComCode(), mailNo).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(JsonUtil.getJsonByObj(getMailInfo("201618060060")));
    }
}
