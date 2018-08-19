package com.platform.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author xiaolu.zhang
 * @desc:用户级别枚举
 * @date: 2018/8/19 17:08
 */
@Getter
@AllArgsConstructor
public enum UserLevelTypeEnum {
    NORMAL(1, "普通用户"),
    VIP(2, "VIP"),
    高级VIP(3, "高级VIP");

    private Integer code;
    private String name;
}