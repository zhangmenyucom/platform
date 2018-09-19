package com.platform.service.impl;

import com.platform.dao.ActivityDao;
import com.platform.entity.ActivityEntity;
import com.platform.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
