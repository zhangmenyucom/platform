package com.platform.entity;

import com.platform.common.OrderStatusEnum;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.platform.common.OrderStatusEnum.*;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
public class OrderVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     **/
    private Integer id;
    /**
     * 订单序列号
     **/
    private String order_sn;
    /**
     * 会员Id
     **/
    private Long user_id;
    /*
    订单状态
    1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除
    2xx 表示订单支付状态　201订单已付款，等待发货
    3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货
    4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货
    */
    private Integer order_status;
    /**
     * 发货状态 商品配送情况;0未发货,1已发货,2已收货,4退货
     **/
    private Integer shipping_status;
    /**
     * 付款状态 支付状态;0未付款;1付款中;2已付款;4退款
     **/
    private Integer pay_status;
    /**
     * 收货人
     **/
    private String consignee;
    /**
     * 国家
     **/
    private String country;
    /**
     * 省
     **/
    private String province;
    /**
     * 地市
     **/
    private String city;
    /**
     * 区县
     **/
    private String district;
    /**
     * 收货地址
     **/
    private String address;
    /**
     * 联系电话
     **/
    private String mobile;
    /**
     * 补充说明
     **/
    private String postscript;
    /**
     * 快递公司Id
     **/
    private Integer shipping_id;
    /**
     * 快递公司code
     **/
    private String shipping_code;
    /**
     * 快递公司名称
     **/
    private String shipping_name;
    /**
     * 快递号
     **/
    private String shipping_no;
    /**
     * 付款
     **/
    private String pay_id;
    /****/
    private String pay_name;
    /**
     * 快递费用
     **/
    private BigDecimal shipping_fee;
    /**
     * 实际需要支付的金额
     **/
    private BigDecimal actual_price;
    /**
     * 积分
     **/
    private Integer integral;
    /**
     * 积分抵扣金额
     **/
    private BigDecimal integral_money;
    /**
     * 订单总价
     **/
    private BigDecimal order_price;
    /**
     * 商品总价
     **/
    private BigDecimal goods_price;
    /**
     * 新增时间
     **/
    private Date add_time;
    /**
     * 确认时间
     **/
    private Date confirm_time;
    /**
     * 付款时间
     **/
    private Date pay_time;
    /**
     * 配送费用
     **/
    private Integer freight_price;
    /**
     * 使用的优惠券id
     **/
    private Integer coupon_id;
    /****/
    private Integer parent_id;
    /**
     * 优惠价格
     **/
    private BigDecimal coupon_price;
    /****/
    private Integer callback_status;
    /****/
    private Integer goodsCount;
    /**
     * 订单的商品
     **/
    private String order_status_text;
    /**
     * 可操作的选项
     **/
    private Map handleOption;
    /**
     * 订单满减
     **/
    private BigDecimal full_cut_price;
    /**
     * 区县
     **/
    private String full_region;
    /**
     * 订单状态
     **/
    private String order_type;

    public String getFull_region() {
        if (StringUtils.isNotEmpty(this.full_region)) {
            return full_region;
        } else {
            StringBuffer strBuff = new StringBuffer();
            if (StringUtils.isNotEmpty(this.country)) {
                strBuff.append(this.country).append(" ");
            }
            if (StringUtils.isNotEmpty(this.province)) {
                strBuff.append(this.province).append(" ");
            }
            if (StringUtils.isNotEmpty(this.city)) {
                strBuff.append(this.city).append(" ");
            }
            if (StringUtils.isNotEmpty(this.district)) {
                strBuff.append(this.district).append(" ");
            }
            this.full_region = strBuff.toString();
            return this.full_region;
        }
    }

    public String getOrder_status_text() {
        if (null != order_status && StringUtils.isEmpty(order_status_text)) {
            order_status_text = "未付款";
            switch (OrderStatusEnum.orderStatusEnumMap.get(order_status)) {
                case CREATED:
                    order_status_text = "未付款";
                    break;
                case WAITTING_SHIP:
                    order_status_text = "等待发货";
                    break;
                case SHIPPED:
                    order_status_text = "待收货";
                    break;
                case CONFIRMED:
                    order_status_text = "已完成";
                    break;
                case PAYED:
                    order_status_text = "已付款";
                    break;
                case CANCEL:
                    order_status_text = "已取消";
                    break;
                case REFUND_WITHOUT_SHIP:
                    order_status_text = "已取消";
                    break;
                case REFUND_WITH_GOODS_RETURNED:
                    order_status_text = "已退货";
                    break;
                default:
                    order_status_text = "";
                    break;
            }
        }
        return order_status_text;
    }

    public Map getHandleOption() {
        handleOption = new HashMap(0);
        /**取消操作**/
        handleOption.put("cancel", false);
        /**删除操作**/
        handleOption.put("delete", false);
        /**支付操作**/
        handleOption.put("pay", false);
        /**评论操作**/
        handleOption.put("comment", false);
        /**确认收货操作**/
        handleOption.put("delivery", false);
        /**完成订单操作**/
        handleOption.put("confirm", false);
        /**退换货操作**/
        handleOption.put("return", false);
        /**再次购买**/
        handleOption.put("buy", false);

        /**订单流程：　下单成功－》支付订单－》发货－》收货－》评论**/
        /**订单相关状态字段设计，采用单个字段表示全部的订单状态**/
        /**1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除**/
        /**2xx 表示订单支付状态　201订单已付款，等待发货**/
        /**3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货**/
        /**4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货**/
        /**如果订单已经取消或是已完成，则可删除和再次购买**/
        if (Objects.equals(order_status, CANCEL.getCode())) {
            handleOption.put("buy", true);
        }

        /**如果订单没有被取消，且没有支付，则可支付，可取消**/
        if (Objects.equals(order_status, CREATED.getCode())) {
            handleOption.put("cancel", true);
            handleOption.put("pay", true);
        }

        /**如果订单已付款，没有发货，则可退款操作**/
        if (Objects.equals(order_status, WAITTING_SHIP.getCode())) {
            handleOption.put("cancel", true);
        }

        /**如果订单已经发货，没有收货，则可收货操作和退款、退货操作**/
        if (Objects.equals(order_status, SHIPPED.getCode())) {
            handleOption.put("confirm", true);
        }

        /**如果订单已经支付，且已经收货，则可完成交易、评论和再次购买**/
        if (Objects.equals(order_status, CONFIRMED.getCode())) {
            handleOption.put("comment", true);
            handleOption.put("buy", true);
        }
        return handleOption;
    }
}
