package com.platform.entity;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 activity
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-22 00:14:02
 */
@Data
public class ActivityEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**  **/
    private Long id;
    /** 活动标题 **/
    private String title;
    /** 作者 **/
    private String author;
    /** 活动内容 **/
    private String content;
    /** 图标地址 **/
    private String banner;
    /** 活动时间 **/
    private Date startdate;
    /** 活动截至日期 **/
    private Date enddate;
    /** 活动地址 **/
    private String position;
}
