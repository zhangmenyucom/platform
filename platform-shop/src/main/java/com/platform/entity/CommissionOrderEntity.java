package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 实体
 * 表名 commission_order
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-24 22:02:34
 */
@Data
public class CommissionOrderEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**佣金所属人**/
    private Long userId;
    /** 佣金订单号 **/
    private String orderSn;
    /** 佣金数额 **/
    private BigDecimal gainBalance;
    /** 订单来源人 **/
    private Long sourceUserId;
    /** 佣金说明 **/
    private String detail;
}
