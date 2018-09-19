package com.platform.service.impl;

import com.platform.dao.GoodsAttributeDao;
import com.platform.entity.GoodsAttributeEntity;
import com.platform.service.GoodsAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("goodsAttributeService")
public class GoodsAttributeServiceImpl extends BaseServiceImpl<GoodsAttributeEntity,GoodsAttributeDao> implements GoodsAttributeService {
	
}
