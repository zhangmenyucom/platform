package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchHistoryEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //关键字
    private String keyword;
    //搜索来源，如PC、小程序、APP等
    private String from;
    //搜索时间
    private Long addTime;
    //会员Id
    private String userId;
    private String userName;

}
