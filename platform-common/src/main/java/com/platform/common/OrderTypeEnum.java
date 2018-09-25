package com.platform.common;

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
public enum OrderTypeEnum {
    CART("1", "购物车"),
    POINT("2", "积分兑换"),
    DIRECT_BUY("4", "立即购买");


    private String code;
    private String desc;

    public  static Map<String, OrderTypeEnum> orderTypeEnumMap = new HashMap<>(OrderTypeEnum.values().length);

    static {
        Arrays.stream(OrderTypeEnum.values()).forEach(item -> orderTypeEnumMap.put(item.getCode(), item));
    }

    OrderTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
