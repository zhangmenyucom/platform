package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class ProductVo extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**商品Id**/
    private Long goods_id;
    /**产品Id**/
    private Long product_id;
    /**商品规格ids**/
    private String goods_specification_ids;
    /**商品序列号**/
    private String goods_sn;
    /**商品库存**/
    private Integer goods_number;
    /**零售价格**/
    private BigDecimal market_price;
    /**时长价**/
    private BigDecimal retail_price;
    /**商品名称**/
    private String goods_name;
    /**商品图片**/
    private String list_pic_url;
}
