package com.platform.entity;/**
 * ${author} on 2018/8/24.
 */

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/24 20:58
 */
@Data
public class WithDrawOrder {
    /**提现id**/
    private Long id;
    /**提现用户id**/
    private Long userId;
    /**提现金额**/
    private BigDecimal withdrawAmount;
    /**提现账户**/
    private String withdrawAccount;
    /**账户类型 0:微信 1：支付宝 3：银行卡**/
    private int withdrawType;
    /**提现状态 0:提交审核 1：审核通过 2：审核不通过 3：提现成功**/
    private int status;
    /**备注**/
    private String comment;
    /**创建时间**/
    private Date createTime;
    /**更新时间**/
    private Date updateTime;

}
