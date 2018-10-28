package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;


/**
 * 商品对应规格表值表
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
public class GoodsSpecificationVo extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    //商品
    private Integer goods_id;
    //规范Id
    private Integer specification_id;
    //规范说明
    private String value;
    private String name;
    //规范图片
    private String pic_url;
}
