package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class CouponVo extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户优惠券主键
     **/
    private Integer user_coupon_id;
    /**
     * 优惠券名称
     **/
    private String name;
    /**
     * 金额
     **/
    private BigDecimal type_money;
    /**
     * 发放方式 0：按订单发放 1：按用户发放 2:商品转发送券 3：按商品发放 4:新用户注册 5：线下发放 6评价好评红包（固定或随机红包） 7包邮
     **/
    private Integer send_type;
    /**
     * 最小金额
     **/
    private BigDecimal min_amount;
    /**
     * 最大金额
     **/
    private BigDecimal max_amount;
    /**
     * 发放时间
     **/
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date send_start_date;
    /**
     * 发放时间
     **/
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date send_end_date;
    /**
     * 使用开始时间
     **/
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date use_start_date;
    /**
     * 使用结束时间
     **/
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date use_end_date;
    /**
     * 最小商品金额
     **/
    private BigDecimal min_goods_amount;
    /**
     * 优惠券说明
     **/
    private String coupon_txt;
    /**
     * 优惠券会员Id
     **/
    private String user_id;
    /**
     * 优惠券编码
     **/
    private String coupon_number;
    /**
     * 可用 1:可用 0：不可用
     **/
    private Integer enabled = 0;
    /**
     * 转发次数
     **/
    private Integer min_transmit_num;
    /**
     * 优惠券状态 1 可用 2 已用 3 过期
     **/
    private Integer coupon_status = 1;
}
