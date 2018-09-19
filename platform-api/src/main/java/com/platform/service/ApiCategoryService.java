package com.platform.service;

import com.platform.dao.ApiCategoryMapper;
import com.platform.entity.CategoryVo;
import com.platform.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class ApiCategoryService extends BaseServiceImpl<CategoryVo,ApiCategoryMapper> {
}
