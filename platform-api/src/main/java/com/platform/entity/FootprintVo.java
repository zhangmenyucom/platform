package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
public class FootprintVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**主键**/
    private Long id;
    /**会员Id**/
    private Long user_id;
    /**商品id**/
    private Integer goods_id;
    /**记录时间**/
    private Date add_time;
    /**推荐人**/
    private Long referrer;

    /** 商品冗余字段**/
    private String name;

    private String list_pic_url;

    private String goods_brief;
    /**实价**/
    private BigDecimal retail_price;
    /** 会员昵称**/
    private String nickname;
    /**会员头像**/
    private String avatar;
}
