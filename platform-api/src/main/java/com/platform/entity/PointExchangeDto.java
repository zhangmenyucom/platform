package com.platform.entity;/**
 * ${author} on 2018/9/25.
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangxiaolu
 * @描述 积分兑换dto
 * @since 2018/9/25 17:25
 */
@Data
public class PointExchangeDto implements Serializable{
    private Long addressId;
    private Long giftId;
    private int giftNumber;
}
