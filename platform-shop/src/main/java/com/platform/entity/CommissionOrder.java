package com.platform.entity;/**
 * ${author} on 2018/8/24.
 */

import java.math.BigDecimal;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/24 9:03
 */
public class CommissionOrder {

    /**主键id**/
    private Long id;
    /**订单id**/
    private Long orderId;
    /**分成收入**/
    private BigDecimal gainBalance;
    /**收入来源人id**/
    private BigDecimal sourceUid;
    /**分成收入说明**/
    private String detail;
}
