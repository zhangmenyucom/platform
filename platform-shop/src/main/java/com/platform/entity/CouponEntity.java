package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 实体
 * 表名 coupon
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-19 12:53:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CouponEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //优惠券名称
    private String name;
    //金额
    private BigDecimal typeMoney;
    //发放方式
    private Integer sendType;
    //最小金额
    private BigDecimal minAmount;
    //最大金额
    private BigDecimal maxAmount;
    //发放时间
    private Date sendStartDate;
    //发放时间
    private Date sendEndDate;
    //使用开始时间
    private Date useStartDate;
    //使用结束时间
    private Date useEndDate;
    //最小商品金额
    private BigDecimal minGoodsAmount;
}
