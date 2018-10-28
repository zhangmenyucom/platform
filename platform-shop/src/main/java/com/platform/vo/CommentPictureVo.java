package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentPictureVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //评价Id
    private Integer comment_id;
    //评价图片
    private String pic_url;
    //排序
    private Integer sort_order;
}
