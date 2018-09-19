package com.platform.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 实体
 * 表名 gift_exchange_record
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-12 18:50:18
 */
@Data
public class GiftExchangeRecordEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 用户id **/
    private Long userId;
    /**昵称**/
    private String nickName;
    /**礼品名称**/
    private String giftName;
    /** 礼品id **/
    private Long giftId;
    /** 使用积分 **/
    private Long usePoint;
    /** 订单号 **/
    private String orderSn;
}
