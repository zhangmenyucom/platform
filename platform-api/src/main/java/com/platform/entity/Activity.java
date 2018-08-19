package com.platform.entity;

import lombok.Data;

import java.time.Instant;

/**
 * @author xiaolu.zhang
 * @desc:活动
 * @date: 2018/8/20 0:45
 */
@Data
public class Activity {
    /**活动id**/
    private int id;
    /**活动标题**/
    private String title;
    /**活动发起人**/
    private String author;
    /**活动内容**/
    private String content;
    /**图片地址**/
    private String banner;
    /**活动开始时间**/
    private Instant startDate;
    /**活动结束时间**/
    private Instant endDate;
    /**活动地址**/
    private String position;
}
