package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 优惠券关联商品实体
 * 表名 coupon_goods
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-29 21:50:17
 */
@Data
public class CouponGoodsEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //优惠券Id
    private Long couponId;
    //商品id
    private Long goodsId;

}
