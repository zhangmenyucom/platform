package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 实体
 * 表名 ad
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-19 09:37:35
 */
@Data
public class AdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //广告位置Id
    private Integer adPositionId;
    //形式
    private Integer mediaType;
    //广告名称
    private String name;
    //链接
    private String link;
    //图片
    private String imageUrl;
    //内容
    private String content;
    //结束时间
    private Date endTime;
    //状态
    private Integer enabled;
}
