package com.platform.entity;

import lombok.Data;
import sun.security.jca.GetInstance;

import java.time.Instant;

/**
 * @author xiaolu.zhang
 * @desc:文章
 * @date: 2018/8/19 19:06
 */
@Data
public class Article {
    /**id**/
    private int id;
    /**文章标题**/
    private String title;
    /**作者**/
    private String author;
    /**文章内容**/
    private String content;
    /**出处**/
    private String sourceUrl;
    /**访问量**/
    private int viewTimes;
    /**状态0 未发布 1:发布**/
    private int status;
    /**创建时间**/
    private Instant createTime;
    /**更新时间**/
    private Instant updateTime;
}
