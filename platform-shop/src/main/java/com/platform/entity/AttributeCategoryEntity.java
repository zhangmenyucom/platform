package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 实体
 * 表名 attribute_category
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-17 16:13:27
 */
@Data
public class AttributeCategoryEntity extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private String name;
    //
    private Integer enabled;
}
