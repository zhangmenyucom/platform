package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 teach_video
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-26 18:25:17
 */
@Data
public class TeachVideoVo extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     **/
    private String title;
    /**
     * 简介
     **/
    private String brief;
    /**
     * 首图
     **/
    private String wrapper;
    /**
     * 视频地址
     **/
    private String videoUrl;
    /**
     * 0:未上架 1：上架
     **/
    private Integer status;
}
