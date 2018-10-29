package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FootprintEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 会员Id
     **/
    private Long userId;
    private String nickName;
    /**
     * 商品id
     **/
    private Integer goodsId;
    private String goodsName;
    /**
     * 记录时间
     **/
    private Date addTime;
}
