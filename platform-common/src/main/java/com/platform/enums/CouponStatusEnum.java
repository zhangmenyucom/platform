package com.platform.enums;/**
 * ${author} on 2018/9/14.
 */

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/9/14 18:47
 */
@Getter
public enum  CouponStatusEnum {

    AVAILABLE(1, "可用"),
    USED(2, "已用"),
    DELETE(3, "已删除");


    private Integer code;
    private String desc;

    public  static Map<Integer, CouponStatusEnum> couponStatusEnumMap = new HashMap<>(CouponStatusEnum.values().length);

    static {
        Arrays.stream(CouponStatusEnum.values()).forEach(item -> couponStatusEnumMap.put(item.code, item));
    }

    CouponStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
