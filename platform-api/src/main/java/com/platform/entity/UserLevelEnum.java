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
public enum UserLevelEnum {
    /**
     * 普通会员
     **/
    NORMAL(1L, "普通会员"),
    /**
     * 会员
     **/
    VIP(2L, "会员"),
    /**
     * 创客
     **/
    CHUANGKE(3L, "创客"),
    /**
     * 城市合伙人
     **/
    HEHUOREN(4L, "城市合伙人");

    UserLevelEnum(Long levelId, String levelName) {
        this.levelId = levelId;
        this.levelName = levelName;
    }

    private Long levelId;

    private String levelName;

    public static Map<Long, UserLevelEnum> LEVEL_MAP = new HashMap<>();

    static {
        Arrays.asList(UserLevelEnum.values()).forEach(e -> LEVEL_MAP.put(e.getLevelId(), e));
    }
}
