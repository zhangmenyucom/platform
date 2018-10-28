package com.platform.service.impl;

import com.platform.dao.ActivityDao;
import com.platform.entity.ActivityEntity;
import com.platform.service.ActivityService;
import org.springframework.stereotype.Service;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-22 00:14:02
 */
@Service("activityService")
public class ActivityServiceImpl extends BaseServiceImpl<ActivityEntity,ActivityDao> implements ActivityService {

}
