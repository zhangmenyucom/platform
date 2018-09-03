package com.platform.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class BuyGoodsVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商品id
     **/
    private Integer goodsId;
    /**
     * 产品id
     **/
    private Integer productId;
    /**
     * 数量
     **/
    private Integer number;
    /**
     * 名称
     **/
    private String name;
}
