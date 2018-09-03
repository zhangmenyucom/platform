package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:08
 */
@Data
public class GoodsAttributeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//商品Id
	private Integer goodsId;
	//属性Id
	private Integer attributeId;
	//属性值
	private String value;

}
