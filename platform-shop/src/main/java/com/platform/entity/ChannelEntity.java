package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 实体
 * 表名 channel
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-22 19:19:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChannelEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //名称
    private String name;
    //url
    private String url;
    //iconUrl
    private String iconUrl;
    //排序
    private Integer sortOrder;
}
