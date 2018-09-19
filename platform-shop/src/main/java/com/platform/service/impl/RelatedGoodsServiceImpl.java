package com.platform.service.impl;

import com.platform.dao.RelatedGoodsDao;
import com.platform.entity.RelatedGoodsEntity;
import com.platform.service.RelatedGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("relatedGoodsService")
public class RelatedGoodsServiceImpl extends  BaseServiceImpl<RelatedGoodsEntity,RelatedGoodsDao> implements RelatedGoodsService {
	
}
