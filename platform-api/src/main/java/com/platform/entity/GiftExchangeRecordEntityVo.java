package com.platform.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 gift_exchange_record
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-13 15:20:44
 */
@Data
@Accessors(chain = true)
public class GiftExchangeRecordEntityVo extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     **/
    private Long userId;
    /**
     * 礼品id
     **/
    private Long giftId;
    /**
     * 用户昵称
     **/
    private String nickName;
    /**
     * 礼品名称
     **/
    private String giftName;
    /**
     * 使用积分
     **/
    private Long usePoint;
    /**
     * 订单号
     **/
    private String orderSn;
}
