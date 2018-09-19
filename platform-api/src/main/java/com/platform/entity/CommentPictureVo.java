package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
public class CommentPictureVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //评价Id
    private Integer comment_id;
    //评价图片
    private String pic_url;
    //排序
    private Integer sort_order;
}
