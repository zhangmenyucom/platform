package com.platform.service;

import com.platform.entity.CouponEntity;
import com.platform.utils.R;

import java.util.Map;

/**
 * Service接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-19 12:53:26
 */
public interface CouponService extends BaseService<CouponEntity>{
    /**
     * 按用户、商品下发优惠券
     *
     * @param params
     * @return
     */
    R publish(Map<String, Object> params);
}
