package com.platform.enums;/**
 * ${author} on 2018/9/6.
 */

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/9/6 14:20
 */
@Getter
public enum PayStatusEnum {
    NOT_PAY(0,"未支付"),
    PAYING(1,"付款中"),
    PAYED(2,"已付款"),
    REFUND(3,"已退款");

    private Integer code;
    private String desc;
    public  static Map<Integer, PayStatusEnum> payStatusEnumMap = new HashMap<>(PayStatusEnum.values().length);

    static {
        Arrays.stream(PayStatusEnum.values()).forEach(item -> payStatusEnumMap.put(item.getCode(), item));
    }


    PayStatusEnum(Integer code, String desc){
        this.code=code;
        this.desc=desc;
    }
}
