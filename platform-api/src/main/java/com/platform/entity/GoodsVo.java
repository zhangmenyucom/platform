package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
public class GoodsVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //商品类型Id
    private Long category_id;
    //商品序列号
    private String goods_sn;
    //名称
    private String name;
    //品牌Id
    private Long brand_id;
    //商品序列号
    private Integer goods_number;
    //关键字
    private String keywords;
    //简明介绍
    private String goods_brief;
    //商品描述
    private String goods_desc;
    //上架
    private Integer is_on_sale;
    //添加时间
    private Date add_time;
    //排序
    private Integer sort_order;
    //删除状态
    private Integer is_delete;
    //属性类别
    private Integer attribute_category;
    //专柜价格
    private BigDecimal counter_price;
    //附加价格
    private BigDecimal extra_price;
    //是否新商品
    private Integer is_new;
    //商品单位
    private String goods_unit;
    //商品主图
    private String primary_pic_url;
    //商品列表图
    private String list_pic_url;
    //市场价
    private BigDecimal market_price;
    //零售价格(现价)
    private BigDecimal retail_price;
    //销售量
    private Integer sell_volume;
    //主sku　product_id
    private Integer primary_product_id;
    //单位价格，单价
    private BigDecimal unit_price;
    //推广描述
    private String promotion_desc;
    //推广标签
    private String promotion_tag;
    //APP专享价
    private BigDecimal app_exclusive_price;
    //是否是APP专属
    private Integer is_app_exclusive;
    //限购
    private Integer is_limited;
    //热销
    private Integer is_hot;
    //购物车中商品数量
    private Integer cart_num = 0;
    // 冗余
    // 产品Id
    private Integer product_id;

}
