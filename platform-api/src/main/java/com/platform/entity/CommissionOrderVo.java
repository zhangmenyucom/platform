package com.platform.entity;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 commission_order
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-25 14:20:46
 */
@Data
public class CommissionOrderVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**  **/
    private Long id;
    /** 佣金订单号 **/
    private String orderSn;
    /** 佣金数额 **/
    private BigDecimal gainBalance;
    /** 订单来源人 **/
    private Long sourceUserId;
    /** 佣金说明 **/
    private String detail;
    /** 创建时间 **/
    private Date createTime;
    /** 更新时间 **/
    private Date updateTime;
}
