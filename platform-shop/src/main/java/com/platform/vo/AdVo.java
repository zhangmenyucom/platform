package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**广告位置Id*/
    private Integer ad_position_id;
    /**形式*/
    private Integer media_type;
    /**广告名称*/
    private String name;
    /**链接*/
    private String link;
    /**图片*/
    private String image_url;
    /**内容*/
    private String content;
    /**结束时间*/
    private Date end_time;
    /**状态*/
    private Integer enabled;
}
