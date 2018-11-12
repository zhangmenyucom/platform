package com.platform.enums;/**
 * ${author} on 2018/9/17.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/9/17 15:22
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {
    SUCCESS(0, "success"),
    FAIL(1, "fail");
    private Integer code;
    private String message;
}
