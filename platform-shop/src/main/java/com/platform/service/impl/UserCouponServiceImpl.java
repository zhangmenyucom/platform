package com.platform.service.impl;

import com.platform.dao.UserCouponDao;
import com.platform.entity.UserCouponEntity;
import com.platform.service.UserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-19 15:40:33
 */
@Service("userCouponService")
public class UserCouponServiceImpl extends BaseServiceImpl<UserCouponEntity,UserCouponDao> implements UserCouponService {

}
