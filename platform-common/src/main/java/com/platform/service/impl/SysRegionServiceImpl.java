package com.platform.service.impl;

import com.platform.dao.SysRegionDao;
import com.platform.entity.SysRegionEntity;
import com.platform.service.SysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-11-04 11:19:31
 */
@Service("regionService")
public class SysRegionServiceImpl  extends  BaseServiceImpl<SysRegionEntity,SysRegionDao> implements SysRegionService {

}
