package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 topic
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-20 14:10:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TopicEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //活动主题
    private String title;
    //活动内容
    private String content;
    //化名
    private String avatar;
    //活动条例图片
    private String itemPicUrl;
    //子标题
    private String subtitle;
    //活动类别
    private Integer topicCategoryId;
    //活动价格
    private BigDecimal priceInfo;
    //
    private String readCount;
    //场景图片链接
    private String scenePicUrl;
    //活动模板Id
    private Integer topicTemplateId;
    //活动标签Id
    private Integer topicTagId;

    /**
     * 实体
     * 表名 article
     *
     * @author taylor
     * @email 516195940@qq.com
     * @date 2018-11-27 16:34:42
     */
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public static class ArticleEntity extends BaseEntity implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * id
         **/
        private Long id;
        /**
         * 商家id
         **/
        private Long merchantId;
        /**
         * 标题
         **/
        private String title;
        /**
         * 作者
         **/
        private String author;
        /**
         * 头图
         **/
        private String bannerPic;
        /**
         * 内容
         **/
        private String content;
        /**
         * 出处
         **/
        private String sourceUrl;
        /**
         * 浏览数
         **/
        private Integer viewTimes;
        /**
         * 顺序
         **/
        private Integer sortOrder;
        /**
         * 状态：0 未上架 1：上架
         **/
        private Integer status;
        /**
         * 创建时间
         **/
        private Date createTime;
        /**
         * 更新时间
         **/
        private Date updateTime;
    }
}
