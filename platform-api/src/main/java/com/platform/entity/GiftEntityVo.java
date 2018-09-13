package com.platform.entity;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 gift
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-13 15:20:44
 */
@Data
public class GiftEntityVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 **/
    private Long id;
    /** 礼品类型 1：线下商品 2：线上商品 **/
    private Integer type;
    /** 礼品名称 **/
    private String picUrl;
    /** 礼品名称 **/
    private String name;
    /** 兑换所需要积分 **/
    private Long pointValue;
    /** 0:下架 1：上架 **/
    private Integer status;
    /** 说明 **/
    private String giftDesc;
    /** 排序 **/
    private Integer sortOrder;
    /** 线上商品id **/
    private Long goodsId;
    /** 创建时间 **/
    private Date createTime;
    /** 更新时间 **/
    private Date updateTime;
}
