package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class SearchHistoryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键**/
    private Long id;
    /**关键字**/
    private String keyword;
    /**搜索来源，如PC、小程序、APP等**/
    private String from;
    /**搜索时间**/
    private Long add_time;
    /**会员Id**/
    private String user_id;
}
