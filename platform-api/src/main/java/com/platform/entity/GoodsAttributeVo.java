package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
public class GoodsAttributeVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //商品Id
    private Integer goods_id;
    //属性Id
    private Integer attribute_id;
    //属性值
    private String value;
    // 冗余
    private String name;
}
