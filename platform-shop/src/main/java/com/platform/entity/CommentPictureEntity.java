package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 评价图片实体
 * 表名 comment_picture
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-29 14:45:55
 */
@Data
public class CommentPictureEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //评价Id
    private Integer commentId;
    //评价图片
    private String picUrl;
    //排序
    private Integer sortOrder;
}
