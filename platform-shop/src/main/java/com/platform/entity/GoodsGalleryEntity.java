package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 商品顶部轮播图
 * 表名 goods_gallery
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-23 14:41:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsGalleryEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //商品id
    private Long goodsId;
    //图片
    private String imgUrl;
    //描述
    private String imgDesc;
    //排序
    private Integer sortOrder;
}
