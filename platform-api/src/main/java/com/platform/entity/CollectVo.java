package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:39
 */
@Data
public class CollectVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //用户Id
    private Long user_id;
    //产品Id
    private Integer value_id;
    //添加时间
    private Long add_time;
    //是否是关注
    private Integer is_attention;
    //
    private Integer type_id;
    //
    private String name;
    private String list_pic_url;
    private String goods_brief;
    private String retail_price;
}
