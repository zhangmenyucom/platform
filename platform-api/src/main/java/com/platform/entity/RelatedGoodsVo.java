package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class RelatedGoodsVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     **/
    private Long id;
    /**
     * 商品Id
     **/
    private Integer goods_id;
    /**
     * 关联商品id
     **/
    private Integer related_goods_id;

}
