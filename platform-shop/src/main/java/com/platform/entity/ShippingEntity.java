package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 实体
 * 表名 shipping
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-09-04 21:42:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ShippingEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private String code;
    //
    private String name;
}
