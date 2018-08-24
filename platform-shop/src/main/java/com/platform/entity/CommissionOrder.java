package com.platform.entity;/**
 * ${author} on 2018/8/24.
 */

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/24 9:03
 */
@Data
public class CommissionOrder implements Serializable {

    /**
     * 主键id
     **/
    private Long id;
    /**
     * 订单id
     **/
    private Long orderId;
    /**
     * 分成收入
     **/
    private BigDecimal gainBalance;
    /**
     * 收入来源人id
     **/
    private BigDecimal sourceUid;
    /**
     * 分成收入说明
     **/
    private String detail;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 更新时间
     **/
    private Date updateTime;
}
