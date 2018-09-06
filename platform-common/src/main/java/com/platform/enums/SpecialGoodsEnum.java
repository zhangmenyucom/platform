package com.platform.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/9/1 16:06
 */

@Getter
public enum SpecialGoodsEnum {
    VIP(1181014, "会员",2),
    CHUANGKE(1181013, "创客",3),
    HEHUOREN(1181012, "合伙人",4);
    private Integer goodsId;
    private String goodsName;
    private Integer roleId;

    public static final Map<Integer, SpecialGoodsEnum> SPECIAL_GOODS_ENUM_MAP = new HashMap<>();

    static {
        Arrays.stream(SpecialGoodsEnum.values()).forEach(e -> SPECIAL_GOODS_ENUM_MAP.put(e.getGoodsId(), e));
    }

    SpecialGoodsEnum(Integer goodsId, String goodsName,Integer roleId) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.roleId=roleId;
    }
}
