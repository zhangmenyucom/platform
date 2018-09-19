package com.platform.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/9/1 16:06
 */

@Getter
public enum SpecialGoodsEnum {
    VIP(1181014L, "会员",2L),
    CHUANGKE(1181013L, "创客",3L),
    HEHUOREN(1181012L, "合伙人",4L);
    private Long goodsId;
    private String goodsName;
    private Long roleId;

    public static final Map<Long, SpecialGoodsEnum> SPECIAL_GOODS_ENUM_MAP = new HashMap<>();

    static {
        Arrays.stream(SpecialGoodsEnum.values()).forEach(e -> SPECIAL_GOODS_ENUM_MAP.put(e.getGoodsId(), e));
    }

    SpecialGoodsEnum(Long goodsId, String goodsName,Long roleId) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.roleId=roleId;
    }
}
