package com.platform.service;

import com.platform.dao.ApiCouponMapper;
import com.platform.service.impl.BaseServiceImpl;
import com.platform.vo.CouponVo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ApiCouponService extends BaseServiceImpl<CouponVo, ApiCouponMapper> {

    public List<CouponVo> queryUserCoupons(Map<String, Object> map) {
        // 检查优惠券是否过期
        List<CouponVo> couponVos = getDao().queryUserCoupons(map);
        for (CouponVo couponVo : couponVos) {
            if (couponVo.getCoupon_status() == 1) {
                // 检查是否过期
                if (couponVo.getUse_end_date().before(new Date())) {
                    Map<String, Object> updateMap = new HashMap<>(2);
                    updateMap.put("status", 3);
                    updateMap.put("userCouponId", couponVo.getUser_coupon_id());
                    getDao().updateUserCoupon(updateMap);
                }
            }
            if (couponVo.getCoupon_status() == 3) {
                // 检查是否不过期
                if (couponVo.getUse_end_date().after(new Date())) {
                    Map<String, Object> updateMap = new HashMap<>(2);
                    updateMap.put("status", 1);
                    updateMap.put("userCouponId", couponVo.getUser_coupon_id());
                    getDao().updateUserCoupon(updateMap);
                }
            }
        }

        return couponVos;
    }

    public CouponVo queryMaxUserEnableCoupon(Map<String, Object> map) {
        return getDao().queryMaxUserEnableCoupon(map);
    }

    public List<CouponVo> queryUserCouponList(Map<String, Object> map) {
        return getDao().queryUserCouponList(map);
    }

    public List<CouponVo> queryCouponByTypes(Map param) {
        return getDao().queryList(param);
    }
}
