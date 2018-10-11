package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 new_gift_record
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-10-11 19:05:18
 */
@Data
public class NewGiftRecordEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     **/
    private Long userId;
    /**
     * 昵称
     **/
    private String nickName;
    /**
     * 头像
     **/
    private String avatar;
    /**
     * 红包
     **/
    private BigDecimal hongBao;
}
