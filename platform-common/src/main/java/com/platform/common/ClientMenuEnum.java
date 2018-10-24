package com.platform.common;/**
 * ${author} on 2018/10/24.
 */

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/10/24 16:57
 */
@Getter
public enum ClientMenuEnum {
    VEDIO(1, "宣传视频", true),
    MALL(2, "微商场", true),
    MERCHANT(3, "商家入住", false),
    POINTEX(4, "礼品兑换", true),
    NAVI(5, "门店导航", true),
    ABOUTUS(6, "关于我们", true),
    NEWGIFT(7, "新人有礼", true),
    DISTRIBUTE(8, "分销中心", true);

    private Integer code;
    private String name;
    private boolean isShow;
    public static Map<ClientMenuEnum, Boolean> clientMenuEnumMap = new HashMap<>(ClientMenuEnum.values().length);

    static {
        Arrays.stream(ClientMenuEnum.values()).forEach(item -> clientMenuEnumMap.put(item, item.isShow()));
    }

    ClientMenuEnum(Integer code, String name, boolean isShow) {
        this.code = code;
        this.name = name;
        this.isShow = isShow;
    }
}
