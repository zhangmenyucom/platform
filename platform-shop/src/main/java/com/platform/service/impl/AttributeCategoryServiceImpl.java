package com.platform.service.impl;

import com.platform.dao.AttributeCategoryDao;
import com.platform.entity.AttributeCategoryEntity;
import com.platform.service.AttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-17 16:13:27
 */
@Service("attributeCategoryService")
public class AttributeCategoryServiceImpl extends  BaseServiceImpl<AttributeCategoryEntity,AttributeCategoryDao> implements AttributeCategoryService {

}
