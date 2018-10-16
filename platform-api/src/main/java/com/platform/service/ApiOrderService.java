package com.platform.service;

import com.alibaba.fastjson.JSONObject;
import com.platform.cache.J2CacheUtils;
import com.platform.common.*;
import com.platform.dao.*;
import com.platform.entity.*;
import com.platform.service.impl.BaseServiceImpl;
import com.platform.utils.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


@Service
public class ApiOrderService extends BaseServiceImpl<OrderVo, ApiOrderMapper> {
    @Autowired
    private ApiAddressMapper apiAddressMapper;
    @Autowired
    private ApiCartMapper apiCartMapper;
    @Autowired
    private ApiCouponMapper apiCouponMapper;
    @Autowired
    private ApiOrderMapper apiOrderMapper;
    @Autowired
    private ApiOrderGoodsMapper apiOrderGoodsMapper;
    @Autowired
    private ApiProductService productService;
    @Autowired
    private ApiGiftService apiGiftService;
    @Autowired
    private ApiUserService apiUserService;
    @Autowired
    private ApiGiftExchangeRecordService apiGiftExchangeRecordService;
    @Autowired
    private ApiGoodsService apiGoodsService;
    @Autowired
    private ApiSeckillGoodsService apiSeckillGoodsService;


    @Transactional
    public Map<String, Object> submit(JSONObject jsonParam, UserVo loginUser) {

        Map<String, Object> resultObj = new HashMap<>(0);
        Long couponId = jsonParam.getLong("couponId");
        Long parentId = jsonParam.getLong("parentId");
        String type = jsonParam.getString("type");
        Long seckillId = jsonParam.getLong("seckillId");
        String postscript = jsonParam.getString("postscript");
        AddressVo addressVo = apiAddressMapper.queryObject(jsonParam.getLong("addressId"));

        Integer freightPrice = 0;
        List<CartVo> checkedGoodsList = new ArrayList<>();

        BigDecimal goodsTotalPrice;
        /**购物车的方式提交订单**/
        if ("cart".equals(type)) {
            Map<String, Object> param = new HashMap<>(0);
            param.put("user_id", loginUser.getUserId());
            param.put("session_id", 1);
            param.put("checked", 1);
            checkedGoodsList = apiCartMapper.queryList(param);
            if (null == checkedGoodsList) {
                resultObj.put("errno", 400);
                resultObj.put("errmsg", "请选择商品");
                return resultObj;
            }
            //统计商品总价
            goodsTotalPrice = new BigDecimal(0.00);
            for (CartVo cartItem : checkedGoodsList) {
                goodsTotalPrice = goodsTotalPrice.add(cartItem.getRetail_price().multiply(new BigDecimal(cartItem.getNumber())));
            }
        } else {
            /**直接购买**/
            BuyGoodsVo goodsVo = (BuyGoodsVo) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME, "goods" + loginUser.getUserId());
            ProductVo productInfo = productService.queryObject(goodsVo.getProductId());
            //计算订单的费用
            //商品总价
            goodsTotalPrice = productInfo.getRetail_price().multiply(new BigDecimal(goodsVo.getNumber()));
            CartVo cartVo = new CartVo();
            BeanUtils.copyProperties(productInfo, cartVo);
            cartVo.setNumber(goodsVo.getNumber());
            cartVo.setGoods_id(goodsVo.getGoodsId());
            cartVo.setProduct_id(goodsVo.getProductId());
            checkedGoodsList.add(cartVo);
        }


        //获取订单使用的优惠券
        BigDecimal couponPrice = new BigDecimal(0.00);
        CouponVo couponVo = null;
        if (couponId != null && couponId != 0) {
            couponVo = apiCouponMapper.getUserCoupon(couponId);
            if (couponVo != null && couponVo.getCoupon_status() == 1) {
                couponPrice = couponVo.getType_money();
            }
        }

        //订单价格计算
        //订单的总价
        BigDecimal orderTotalPrice = goodsTotalPrice.add(new BigDecimal(freightPrice));
        //减去其它支付的金额后，要实际支付的金额
        BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice).compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.valueOf(0.01) : orderTotalPrice.subtract(couponPrice);

        /**秒杀价**/
        if ("seckill".equals(type)) {
            SeckillGoodsVo seckillGoodsVo = apiSeckillGoodsService.queryObject(seckillId);
            actualPrice = seckillGoodsVo.getCurPrice();
        }
        OrderVo orderInfo = new OrderVo();
        orderInfo.setOrder_sn(CommonUtil.generateOrderNumber());
        orderInfo.setUser_id(loginUser.getUserId());
        //收货地址和运费
        orderInfo.setConsignee(addressVo.getUserName());
        orderInfo.setMobile(addressVo.getTelNumber());
        orderInfo.setCountry(addressVo.getNationalCode());
        orderInfo.setProvince(addressVo.getProvinceName());
        orderInfo.setCity(addressVo.getCityName());
        orderInfo.setDistrict(addressVo.getCountyName());
        orderInfo.setAddress(addressVo.getDetailInfo());
        //
        orderInfo.setFreight_price(freightPrice);
        //留言
        orderInfo.setPostscript(postscript);
        //使用的优惠券
        orderInfo.setCoupon_id(couponId);
        orderInfo.setCoupon_price(couponPrice);
        orderInfo.setAdd_time(new Date());
        orderInfo.setGoods_price(goodsTotalPrice);
        orderInfo.setOrder_price(orderTotalPrice);
        orderInfo.setActual_price(actualPrice);
        // 待付款
        orderInfo.setOrder_status(0);
        orderInfo.setShipping_status(0);
        orderInfo.setPay_status(0);
        orderInfo.setShipping_id(0);
        orderInfo.setShipping_fee(new BigDecimal(0));
        orderInfo.setIntegral(0);
        orderInfo.setMerchantId(loginUser.getMerchantId());
        orderInfo.setIntegral_money(new BigDecimal(0));
        if ("cart".equals(type)) {
            orderInfo.setOrder_type(OrderTypeEnum.CART.getCode());
        } else if ("seckill".equals(type)) {
            orderInfo.setOrder_type(OrderTypeEnum.SECKILL.getCode());
        } else {
            orderInfo.setOrder_type(OrderTypeEnum.DIRECT_BUY.getCode());
        }

        /**添加引荐人id(特殊商品)**/
        orderInfo.setParent_id(parentId);
        orderInfo.setMerchantId(loginUser.getMerchantId());
        //开启事务，插入订单信息和订单商品
        apiOrderMapper.save(orderInfo);
        if (null == orderInfo.getId()) {
            resultObj.put("errno", 1);
            resultObj.put("errmsg", "订单提交失败");
            return resultObj;
        }
        //统计商品总价
        List<OrderGoodsVo> orderGoodsData = new ArrayList<>();
        for (CartVo goodsItem : checkedGoodsList) {
            OrderGoodsVo orderGoodsVo = new OrderGoodsVo();
            orderGoodsVo.setOrder_id(orderInfo.getId());
            orderGoodsVo.setGoods_id(goodsItem.getGoods_id());
            orderGoodsVo.setGoods_sn(goodsItem.getGoods_sn());
            orderGoodsVo.setProduct_id(goodsItem.getProduct_id());
            orderGoodsVo.setGoods_name(goodsItem.getGoods_name());
            orderGoodsVo.setList_pic_url(goodsItem.getList_pic_url());
            orderGoodsVo.setMarket_price(goodsItem.getMarket_price());
            orderGoodsVo.setRetail_price(goodsItem.getRetail_price());
            orderGoodsVo.setNumber(goodsItem.getNumber());
            orderGoodsVo.setGoods_specifition_name_value(goodsItem.getGoods_specifition_name_value());
            orderGoodsVo.setGoods_specifition_ids(goodsItem.getGoods_specifition_ids());
            orderGoodsVo.setMerchantId(loginUser.getMerchantId());
            orderGoodsData.add(orderGoodsVo);
            apiOrderGoodsMapper.save(orderGoodsVo);
            /**减库存**/
            GoodsVo goodsVo = apiGoodsService.queryObject(orderGoodsVo.getGoods_id());
            if (goodsVo.getGoods_number() != null && orderGoodsVo.getNumber() != null) {
                goodsVo.setGoods_number(goodsVo.getGoods_number() - orderGoodsVo.getNumber());
                apiGoodsService.update(goodsVo);
            }
            /**如果是秒杀减秒杀库存**/
            if ("seckill".equals(type)) {
                SeckillGoodsVo seckillGoodsVo = apiSeckillGoodsService.queryObject(seckillId);
                seckillGoodsVo.setSold(seckillGoodsVo.getSold() + orderGoodsVo.getNumber());
                apiSeckillGoodsService.update(seckillGoodsVo);
            }
        }

        //清空已购买的商品
        if ("cart".equals(type)) {
            apiCartMapper.deleteByCart(loginUser.getUserId(), 1, 1);
        }
        resultObj.put("errno", 0);
        resultObj.put("errmsg", "订单提交成功");

        Map<String, OrderVo> orderInfoMap = new HashMap<>(0);
        orderInfoMap.put("orderInfo", orderInfo);
        resultObj.put("data", orderInfoMap);

        // 优惠券标记已用
        if (couponVo != null && couponVo.getCoupon_status() == 1) {
            Map<String, Object> map = new HashMap<>(0);
            map.put("orderSn", orderInfo.getOrder_sn());
            map.put("useDate", new Date());
            map.put("userCouponId", couponVo.getUser_coupon_id());
            map.put("status", CouponStatusEnum.USED.getCode());
            apiCouponMapper.updateUserCoupon(map);
        }
        return resultObj;
    }

    public void exchangePoint(PointExchangeDto pointExchangeDto, UserVo loginUser) throws Exception {
        UserVo userVo = apiUserService.queryObject(loginUser.getUserId());
        GiftEntityVo giftEntityVo = apiGiftService.queryObject(pointExchangeDto.getGiftId());
        if (userVo.getPoint() < giftEntityVo.getPointValue() * pointExchangeDto.getGiftNumber()) {
            throw new Exception("积分不足");
        }
        String orderSn = CommonUtil.generateOrderNumber();
        if (giftEntityVo.getGoodsId() != null && pointExchangeDto.getAddressId() != null) {
            AddressVo addressVo = apiAddressMapper.queryObject(pointExchangeDto.getAddressId());
            OrderVo orderInfo = new OrderVo();
            orderInfo.setOrder_status(OrderStatusEnum.WAITTING_SHIP.getCode())
                    .setActual_price(BigDecimal.ZERO)
                    .setAdd_time(new Date())
                    .setUser_id(userVo.getUserId())
                    .setOrder_sn(orderSn)
                    .setOrder_price(BigDecimal.ZERO)
                    .setOrder_type(OrderTypeEnum.POINT.getCode())
                    .setPay_status(PayStatusEnum.PAYED.getCode())
                    .setShipping_status(ShippingStatusEnum.NOT_SHIPPED.getCode())
                    //收货地址和运费
                    .setConsignee(addressVo.getUserName())
                    .setMobile(addressVo.getTelNumber())
                    .setProvince(addressVo.getProvinceName())
                    .setCity(addressVo.getCityName())
                    .setDistrict(addressVo.getCountyName())
                    .setAddress(addressVo.getDetailInfo())
                    .setIntegral(0)
                    .setGoods_price(BigDecimal.ZERO)
                    .setIntegral_money(BigDecimal.ZERO)
                    .setGoodsCount(pointExchangeDto.getGiftNumber())
                    .setMerchantId(userVo.getMerchantId());
            apiOrderMapper.save(orderInfo);

            OrderGoodsVo orderGoodsVo = new OrderGoodsVo();

            GoodsVo goodsVo = apiGoodsService.queryObject(giftEntityVo.getGoodsId());
            List<ProductVo> productVos = productService.queryProductByGoodsId(goodsVo.getId());
            //ProductVo productVo = productService.queryObject(goodsVo.getProduct_id());
            orderGoodsVo.setOrder_id(orderInfo.getId());
            orderGoodsVo.setGoods_id(giftEntityVo.getGoodsId());
            orderGoodsVo.setGoods_sn(productVos.get(0).getGoods_sn());
            orderGoodsVo.setProduct_id(productVos.get(0).getId());
            orderGoodsVo.setGoods_name(goodsVo.getName());
            orderGoodsVo.setList_pic_url(goodsVo.getList_pic_url());
            orderGoodsVo.setMarket_price(goodsVo.getMarket_price());
            orderGoodsVo.setRetail_price(goodsVo.getMarket_price());
            orderGoodsVo.setNumber(pointExchangeDto.getGiftNumber());
            orderGoodsVo.setMerchantId(userVo.getMerchantId());
            apiOrderGoodsMapper.save(orderGoodsVo);
        }
        /**记录兑换记录**/
        GiftExchangeRecordEntityVo giftExchangeRecordEntityVo = new GiftExchangeRecordEntityVo();
        giftExchangeRecordEntityVo.setGiftId(giftEntityVo.getId())
                .setUsePoint(giftEntityVo.getPointValue() * pointExchangeDto.getGiftNumber())
                .setUserId(userVo.getUserId())
                .setOrderSn(orderSn)
                .setNumber(pointExchangeDto.getGiftNumber())
                .setCreateTime(new Date())
                .setUpdateTime(new Date())
                .setMerchantId(loginUser.getMerchantId());
        apiGiftExchangeRecordService.save(giftExchangeRecordEntityVo);
        /**更新积分**/
        apiUserService.update(new UserVo().setPoint(userVo.getPoint() - giftEntityVo.getPointValue()).setUserId(userVo.getUserId()));
    }

    public OrderVo queryByOrderSn(String orderSn) {
        return this.getDao().queryByOrderSn(orderSn);
    }
}
