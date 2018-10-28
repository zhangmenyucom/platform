package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 规格表
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SpecificationEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//规范名称
	private String name;
	//排序
	private Integer sortOrder;

}
