package com.platform.service.impl;

import com.platform.dao.AttributeDao;
import com.platform.entity.AttributeEntity;
import com.platform.service.AttributeService;
import org.springframework.stereotype.Service;


@Service("attributeService")
public class AttributeServiceImpl extends  BaseServiceImpl<AttributeEntity,AttributeDao> implements AttributeService {

}
