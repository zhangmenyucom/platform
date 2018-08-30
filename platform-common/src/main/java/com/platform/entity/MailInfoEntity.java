package com.platform.entity;/**
 * ${author} on 2018/8/30.
 */

import lombok.Data;

import java.util.List;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/30 8:49
 */
@Data
public class MailInfoEntity {
    private String message;
    private String nu;
    private String ischeck;
    private String condition;
    private String com;
    private int status;
    private int state;
    private List<MailTimeInfo> data;
}


@Data
class MailTimeInfo {
    private String time;
    private String ftime;
    private String context;
    private String location;
}
