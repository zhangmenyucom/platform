package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 实体
 * 表名 user_coupon
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-19 15:40:33
 */
@Data
public class UserCouponEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     **/
    private Integer id;
    /**
     * 优惠券Id
     **/
    private Integer couponId;
    /**
     * 优惠券数量
     **/
    private String couponNumber;
    /**
     * 会员Id
     **/
    private Integer userId;

    /**昵称**/
    private String nickname;
    /**
     * 使用时间
     **/
    private Date usedTime;
    /**
     * 领取时间
     **/
    private Date addTime;
    /**
     * 订单号
     **/
    private String  orderSn;
    /**
     * 会员名
     **/
    private String userName;
    /**
     * 优惠劵名称
     **/
    private String couponName;
}
