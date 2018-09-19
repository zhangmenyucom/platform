package com.platform.service.impl;

import com.platform.dao.CouponGoodsDao;
import com.platform.entity.CouponGoodsEntity;
import com.platform.service.CouponGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 优惠券关联商品Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-29 21:50:17
 */
@Service("couponGoodsService")
public class CouponGoodsServiceImpl extends BaseServiceImpl<CouponGoodsEntity,CouponGoodsDao> implements CouponGoodsService {
}
