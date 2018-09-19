package com.platform.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaolu.zhang
 * @desc: 短信类型枚举
 * @date: 2018/8/18 0:26
 */
@AllArgsConstructor
@Getter
public enum  SMSTypeEnum {
     CHUANGYUN(2,"创瑞"),
     TENGXUNYUN(1,"腾讯云");

    private int code;

    private String name;
}
