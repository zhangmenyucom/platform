package com.platform.utils;/**
 * ${author} on 2018/8/30.
 */

import com.platform.entity.MailCompanyResponse;
import com.platform.entity.MailInfoEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/30 8:42
 */
public interface HttpApi {
    /**
     * 查询快递可能公司
     **/
    @GET("/autonumber/autoComNum")
    Call<MailCompanyResponse> getMailCompanyInfo(@Query("text") String mailNo);

    /**
     * 查询快递可能公司
     **/
    @GET("/query")
    Call<MailInfoEntity> getMailInfo(@Query("type") String type, @Query("postid") String postId);

}
