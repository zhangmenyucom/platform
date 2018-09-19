package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class TopicCategoryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键**/
    private Long id;
    /**活动类别主题**/
    private String title;
    /**活动类别图片链接**/
    private String pic_url;
}
