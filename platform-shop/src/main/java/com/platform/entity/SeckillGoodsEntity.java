package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 seckill_goods
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-10-11 11:18:21
 */
@Data
public class SeckillGoodsEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商品id
     **/
    private Long goodsId;
    /**
     * 库存
     **/
    private Long stock;
    /**
     * 已售
     **/
    private Long sold;
    /**
     * 秒杀价
     **/
    private BigDecimal curPrice;
    /**
     * 开始日期
     **/
    private Date startTime;
    /**
     * 截止日期
     **/
    private Date endTime;
    /**
     * 描述
     **/
    private String description;
    /**
     * 商品名称
     **/
    private String goodsName;
    /**
     * 商品图片
     **/
    private String goodsPic;
    /**
     * 商品原价
     **/
    private BigDecimal marketPrice;
}
