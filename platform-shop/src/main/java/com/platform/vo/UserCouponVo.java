package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class UserCouponVo extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    /**优惠券Id**/
    private Long coupon_id;
    /**优惠券数量**/
    private String coupon_number;
    /**会员Id**/
    private Long user_id;
    /**使用时间**/
    private Date used_time;
    /**领取时间**/
    private Date add_time;
    /**订单orderSn**/
    private String orderSn;
    /**来源key**/
    private String source_key;
    /**分享人**/
    private Long referrer;
}
