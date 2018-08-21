package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 实体表名 sms_log
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-25 10:04:52
 */
@Data
public class SmsLogVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long user_id;
    private String phone;
    private Long log_date;
    /** 发送模板**/
    private String sms_code;
    /** 1成功 0失败**/
    private Integer send_status;
    /****/
    private String sms_text;
}
