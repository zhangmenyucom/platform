package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 sms_log
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-09 04:53:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmsLogEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**  **/
    private Integer userId;
    /**  **/
    private String nickName;
    /**  **/
    private String phone;
    /**  **/
    private Date logDate;
    /**  **/
    private String smsCode;
    /**  **/
    private Long sendStatus;
    /** 1成功 0失败 **/
    private String smsText;
}
