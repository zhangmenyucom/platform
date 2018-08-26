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
public class TeachVideoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 **/
    private Long id;
    /** 名称 **/
    private String title;
    /** 简介 **/
    private String brief;
    /** 视频地址 **/
    private String videoUrl;
    /** 0:未上架 1：上架 **/
    private Integer status;
    /** 创建时间 **/
    private Date createTime;
    /** 更新时间 **/
    private Date updateTime;
}
