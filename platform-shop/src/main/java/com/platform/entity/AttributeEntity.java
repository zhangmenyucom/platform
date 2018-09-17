package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 实体
 * 表名 attribute
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-17 16:48:17
 */
@Data
public class AttributeEntity extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    //所属种类
    private Integer attributeCategoryId;
    //名称
    private String name;
    //类型
    private Integer inputType;
    //值
    private String value;
    //排序
    private Integer sortOrder;
}
