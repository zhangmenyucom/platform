package com.platform.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/9/6 14:03
 */
@Getter
public enum CouponTypeEnum {
    ORDER_COUPON("1", "订单券"),
    USER_COUPON("2", "用户券"),
    GOODS_COUPON("3", "商品券"),
    NEWER_COUPON("4", "新用户券"),
    OFFLINE_COUPON("5", "线下发放"),
    COMMENT_COUPON("6", "好评券"),
    ALL_COUPON("7", "平台券");

    private String code;
    private String desc;

    public static Map<String, CouponTypeEnum> couponTypeEnumMap = new HashMap<>(CouponTypeEnum.values().length);

    static {
        Arrays.stream(CouponTypeEnum.values()).forEach(item -> couponTypeEnumMap.put(item.getCode(), item));
    }

    CouponTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
