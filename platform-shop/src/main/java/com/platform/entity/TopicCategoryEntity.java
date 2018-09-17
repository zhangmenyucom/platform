package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 实体
 * 表名 topic_category
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-20 15:41:56
 */
@Data
public class TopicCategoryEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //活动类别主题
    private String title;
    //活动类别图片链接
    private String picUrl;
}
