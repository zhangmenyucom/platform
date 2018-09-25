package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 实体
 * 表名 brand
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-19 17:59:15
 */
@Data
public class BrandEntity extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**品牌名称**/
    private String name;
    /**图片**/
    private String listPicUrl;
    /**描述**/
    private String simpleDesc;
    /**图片**/
    private String picUrl;
    /**排序**/
    private Integer sortOrder;
    /**显示**/
    private Integer isShow;
    /****/
    private BigDecimal floorPrice=BigDecimal.ZERO;
    //app显示图片
    private String appListPicUrl;
    /**新品牌**/
    private Integer isNew;
    /**图片**/
    private String newPicUrl;
    /**排序**/
    private Integer newSortOrder;
}
