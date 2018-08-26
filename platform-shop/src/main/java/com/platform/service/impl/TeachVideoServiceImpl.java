package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.TeachVideoDao;
import com.platform.entity.TeachVideoEntity;
import com.platform.service.TeachVideoService;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-26 18:25:17
 */
@Service("teachVideoService")
public class TeachVideoServiceImpl implements TeachVideoService {
    @Autowired
    private TeachVideoDao teachVideoDao;

    @Override
    public TeachVideoEntity queryObject(Long id) {
        return teachVideoDao.queryObject(id);
    }

    @Override
    public List<TeachVideoEntity> queryList(Map<String, Object> map) {
        return teachVideoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return teachVideoDao.queryTotal(map);
    }

    @Override
    public int save(TeachVideoEntity teachVideo) {
        return teachVideoDao.save(teachVideo);
    }

    @Override
    public int update(TeachVideoEntity teachVideo) {
        return teachVideoDao.update(teachVideo);
    }

    @Override
    public int delete(Long id) {
        return teachVideoDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return teachVideoDao.deleteBatch(ids);
    }
}
