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
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;

    @Override
    public ActivityEntity queryObject(Long id) {
        return activityDao.queryObject(id);
    }

    @Override
    public List<ActivityEntity> queryList(Map<String, Object> map) {
        return activityDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return activityDao.queryTotal(map);
    }

    @Override
    public int save(ActivityEntity activity) {
        return activityDao.save(activity);
    }

    @Override
    public int update(ActivityEntity activity) {
        return activityDao.update(activity);
    }

    @Override
    public int delete(Long id) {
        return activityDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return activityDao.deleteBatch(ids);
    }
}
