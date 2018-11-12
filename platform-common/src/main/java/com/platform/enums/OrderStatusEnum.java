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
public enum OrderStatusEnum {
    CREATED(0, "创建订单未支付"),
    CANCEL(101, "已取消"),
    DELETE(102, "已删除"),
    PAYED(200, "已支付"),
    WAITTING_SHIP(201, "待发货"),
    SHIPPED(300, "已发货"),
    CONFIRMED(301, "已收货"),
    REFUND_WITHOUT_SHIP(401, "未发货退款"),
    REFUND_WITH_GOODS_RETURNED(402, "退款退货");


    private Integer code;
    private String desc;

    public  static Map<Integer, OrderStatusEnum> orderStatusEnumMap = new HashMap<>(OrderStatusEnum.values().length);

    static {
        Arrays.stream(OrderStatusEnum.values()).forEach(item -> orderStatusEnumMap.put(item.getCode(), item));
    }

    OrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
