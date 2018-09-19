package com.platform.service.impl;

import com.platform.dao.OrderGoodsDao;
import com.platform.entity.OrderGoodsEntity;
import com.platform.service.OrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("orderGoodsService")
public class OrderGoodsServiceImpl extends BaseServiceImpl<OrderGoodsEntity,OrderGoodsDao> implements OrderGoodsService {

}
