package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:39
 */
@Data
public class BrandVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**品牌名称**/
    private String name;
    /**图片**/
    private String list_pic_url;
    /**描述**/
    private String simple_desc;
    /**图片**/
    private String pic_url;
    /**排序**/
    private Integer sort_order;
    /**显示**/
    private Integer is_show;
    /****/
    private BigDecimal floor_price;
    /**app显示图片**/
    private String app_list_pic_url;
    /**新品牌**/
    private Integer is_new;
    /**图片**/
    private String new_pic_url;
    /**排序**/
    private Integer new_sort_order;
}
