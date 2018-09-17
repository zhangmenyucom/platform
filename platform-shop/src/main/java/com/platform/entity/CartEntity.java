package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:06
 */
@Data
public class CartEntity  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //会员Id
    private Long userId;
    private String userName;
    //sessionId
    private String sessionId;
    //商品Id
    private Integer goodsId;
    //商品序列号
    private String goodsSn;
    //产品Id
    private Integer productId;
    //产品名称
    private String goodsName;
    //市场价
    private BigDecimal marketPrice;
    //零售价格
    private BigDecimal retailPrice;
    //数量
    private Integer number;
    //规格属性组成的字符串，用来显示用
    private String goodsSpecifitionNameValue;
    //product表对应的goods_specifition_ids
    private String goodsSpecifitionIds;
    //
    private Integer checked;
    //商品图片
    private String listPicUrl;
}
