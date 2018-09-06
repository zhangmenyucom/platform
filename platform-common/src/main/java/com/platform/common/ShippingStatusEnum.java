package com.platform.common;/**
 * ${author} on 2018/9/6.
 */

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/9/6 14:26
 */
@Getter
public enum ShippingStatusEnum {
    NOT_SHIPPED(0, "未发货"),
    SHIPPED(1, "已发货"),
    CONFIRMED(2, "已收货"),
    RETURNED(4, "已退货");
    private Integer code;
    private String desc;
    public  static Map<Integer, ShippingStatusEnum> shipStatusEnumMap = new HashMap<>(ShippingStatusEnum.values().length);

    static {
        Arrays.stream(ShippingStatusEnum.values()).forEach(item -> shipStatusEnumMap.put(item.getCode(), item));
    }

    ShippingStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
