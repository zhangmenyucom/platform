package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class AttributeVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer attribute_category_id;
    //
    private String name;
    //
    private Integer input_type;
    //
    private String value;
    //
    private Integer sort_order;
}
