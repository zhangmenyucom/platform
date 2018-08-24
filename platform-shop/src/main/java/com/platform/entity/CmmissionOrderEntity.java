package com.platform.entity;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 cmmission_order
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-24 22:02:34
 */
@Data
public class CmmissionOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**  **/
    private Long id;
    /** 佣金订单号 **/
    private Long orderId;
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
