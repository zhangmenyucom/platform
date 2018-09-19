package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class ChannelVo  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private String name;
    //
    private String url;
    //
    private String icon_url;
    //
    private Integer sort_order;

}
