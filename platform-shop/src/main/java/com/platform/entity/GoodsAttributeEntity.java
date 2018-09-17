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
public class GoodsAttributeEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	//商品Id
	private Long goodsId;
	//属性Id
	private Long attributeId;
	//属性值
	private String value;

}
