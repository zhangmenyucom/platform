package com.platform.service.impl;

import com.platform.dao.BrandDao;
import com.platform.entity.BrandEntity;
import com.platform.service.BrandService;
import org.springframework.stereotype.Service;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-19 17:59:15
 */
@Service("brandService")
public class BrandServiceImpl extends BaseServiceImpl<BrandEntity,BrandDao> implements BrandService {

}
