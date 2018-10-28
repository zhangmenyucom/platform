package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 热闹关键词表实体
 * 表名 keywords
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-25 21:23:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KeywordsEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private String keyword;
    //
    private Integer isHot;
    //
    private Integer isDefault;
    //
    private Integer isShow;
    //
    private Integer sortOrder;
    //关键词的跳转链接
    private String schemeUrl;
    //
    private Integer type;

}
