package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 实体
 * 表名 product
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-30 14:31:21
 */
@Data
public class ProductEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**商品Id**/
    private Long goodsId;
    /**商品规格ids**/
    private String goodsSpecificationIds;
    /**商品序列号**/
    private String goodsSn;
    /**商品库存**/
    private Integer goodsNumber;
    /**零售价格**/
    private BigDecimal retailPrice;
    /**市场价格**/
    private BigDecimal marketPrice;
    /**商品**/
    private String goodsName;
    /** **/
    private String specificationValue;
}
