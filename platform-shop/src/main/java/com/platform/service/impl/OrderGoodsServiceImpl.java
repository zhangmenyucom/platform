package com.platform.service.impl;

import com.platform.dao.OrderGoodsDao;
import com.platform.entity.OrderGoodsEntity;
import com.platform.service.OrderGoodsService;
import org.springframework.stereotype.Service;


@Service("orderGoodsService")
public class OrderGoodsServiceImpl extends BaseServiceImpl<OrderGoodsEntity,OrderGoodsDao> implements OrderGoodsService {

}
