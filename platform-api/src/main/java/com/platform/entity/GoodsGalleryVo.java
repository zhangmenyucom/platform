package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
public class GoodsGalleryVo  extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    //商品id
    private Integer goods_id;
    //图片
    private String img_url;
    //描述
    private String img_desc;
    //排序
    private Integer sort_order;

}
