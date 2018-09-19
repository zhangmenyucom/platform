package com.platform.service;

import com.platform.dao.ApiProductMapper;
import com.platform.entity.ProductVo;
import com.platform.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class ApiProductService extends BaseServiceImpl<ProductVo, ApiProductMapper> {

}
