package com.platform.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * 名称：SmsConfig <br>
 * 描述：短信配置信息<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
@Data
public class SmsConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 类型 2：创瑞 1:腾讯云
     */
    @Range(min = 1, max = 3, message = "类型错误")
    private Integer type;

    /**
     * 短信发送域名
     */
    private String domain;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码(md5加密)
     */
    private String pwd;

    /**
     * 签名
     */
    private String chuangRuiSign;


    /**腾讯云配置**/
    private Integer appid;

    /**appkey**/
    private String appkey;

    /**模板id**/
    private Integer templateId;

    /**
     * 签名
     */
    private String tengXunYunSign;

}
