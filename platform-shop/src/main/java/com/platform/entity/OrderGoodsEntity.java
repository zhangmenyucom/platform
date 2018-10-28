package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderGoodsEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	//订单Id
	private Integer orderId;
	//商品id
	private Integer goodsId;
	//商品名称
	private String goodsName;
	//商品序列号
	private String goodsSn;
	//产品Id
	private Integer productId;
	//商品数量
	private Integer number;
	//市场价
	private BigDecimal marketPrice;
	//零售价格
	private BigDecimal retailPrice;
	//商品规格详情
	private String goodsSpecifitionNameValue;
	//虚拟商品
	private Integer isReal;
	//商品规格Ids
	private String goodsSpecifitionIds;
	//图片链接
	private String listPicUrl;
}
