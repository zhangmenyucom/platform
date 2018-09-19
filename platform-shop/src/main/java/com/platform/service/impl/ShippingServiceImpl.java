package com.platform.service.impl;

import com.platform.dao.ShippingDao;
import com.platform.entity.ShippingEntity;
import com.platform.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-09-04 21:42:24
 */
@Service("shippingService")
public class ShippingServiceImpl extends BaseServiceImpl<ShippingEntity,ShippingDao> implements ShippingService {

}
