package com.platform.entity;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 withdraw_order
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-24 22:02:34
 */
@Data
public class WithdrawOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键id **/
    private Long id;
    /**订单号**/
    private String orderSn;
    /** 提现人id **/
    private Long userId;
    /** 提现金额 **/
    private BigDecimal withdrawAmount;
    /** 提现账户 **/
    private String withdrawAccount;
    /** 账户类型 0:微信 1：支付宝 3：银行卡 **/
    private Integer accountType;
    /** 提现状态 0:提交审核 1：审核通过 2：审核不通过 3：提现成功 **/
    private Integer status;
    /** 备注 **/
    private String comment;
    /** 创建时间 **/
    private Date createTime;
    /** 更新时间 **/
    private Date updateTime;
}
