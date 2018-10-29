package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderGoodsVo  extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单Id
     **/
    private Long order_id;
    /**
     * 商品id
     **/
    private Long goods_id;
    /**
     * 商品名称
     **/
    private String goods_name;
    /**
     * 商品序列号
     **/
    private String goods_sn;
    /**
     * 产品Id
     **/
    private Long product_id;
    /**
     * 商品数量
     **/
    private Integer number;
    /**
     * 市场价
     **/
    private BigDecimal market_price;
    /**
     * 零售价格
     **/
    private BigDecimal retail_price;
    /**
     * 商品规格详情
     **/
    private String goods_specifition_name_value;
    /**
     * 虚拟商品
     **/
    private Integer is_real;
    /**
     * 商品规格Ids
     **/
    private String goods_specifition_ids;
    /**
     * 图片链接
     ****/
    private String list_pic_url;
}
