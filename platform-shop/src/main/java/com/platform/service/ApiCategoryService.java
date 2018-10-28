package com.platform.service;

import com.platform.dao.ApiCategoryMapper;
import com.platform.service.impl.BaseServiceImpl;
import com.platform.vo.CategoryVo;
import org.springframework.stereotype.Service;


@Service
public class ApiCategoryService extends BaseServiceImpl<CategoryVo,ApiCategoryMapper> {
}
