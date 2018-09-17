package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 规格表
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:10
 */
@Data
public class SpecificationEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//规范名称
	private String name;
	//排序
	private Integer sortOrder;

}
