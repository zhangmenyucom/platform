package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchHistoryVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**关键字**/
    private String keyword;
    /**搜索来源，如PC、小程序、APP等**/
    private String from;
    /**搜索时间**/
    private Long add_time;
    /**会员Id**/
    private String user_id;
}
