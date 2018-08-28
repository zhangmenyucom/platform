package com.platform.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/8/28 22:23
 */
@Getter
public enum UserLeveEnum {
    /**
     * 普通会员
     **/
    NORMAL(1, "普通会员"),
    /**
     * 会员
     **/
    VIP(2, "会员"),
    /**
     * 创客
     **/
    CHUANGKE(3, "创客"),
    /**
     * 城市合伙人
     **/
    HEHUOREN(4, "城市合伙人");

    UserLeveEnum(Integer levelId, String levelName) {
        this.levelId = levelId;
        this.levelName = levelName;
    }

    private Integer levelId;

    private String levelName;

    public static Map<Integer, UserLeveEnum> LEVEL_MAP = new HashMap<>();

    static {
        Arrays.asList(UserLeveEnum.values()).forEach(e -> {
            LEVEL_MAP.put(e.getLevelId(), e);
        });
    }
}
