package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:09
 */
@Data
public class RelatedGoodsEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	//商品Id
	private Integer goodsId;
	//关联商品id
	private Integer relatedGoodsId;
}
