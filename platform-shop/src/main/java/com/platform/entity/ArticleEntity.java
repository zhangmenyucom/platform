package com.platform.entity;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 article
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-22 00:14:02
 */
@Data
public class ArticleEntity extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 标题 **/
    private String title;
    /** 头图 **/
    private String bannerPic;
    /** 作者 **/
    private String author;
    /** 内容 **/
    private String content;
    /** 出处 **/
    private String sourceUrl;
    /** 浏览数 **/
    private Integer viewTimes;
    /** 顺序 **/
    private Integer sortOrder;
    /** 状态：0 未上架 1：上架 **/
    private Integer status;
}
