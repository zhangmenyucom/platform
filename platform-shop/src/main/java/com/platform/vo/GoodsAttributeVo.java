package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsAttributeVo extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    //商品Id
    private Integer goods_id;
    //属性Id
    private Integer attribute_id;
    //属性值
    private String value;
    // 冗余
    private String name;
}
