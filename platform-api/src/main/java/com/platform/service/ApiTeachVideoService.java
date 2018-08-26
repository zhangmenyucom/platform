package com.platform.service;

import com.platform.dao.ApiTeachVideoMapper;
import com.platform.entity.TeachVideoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-26 18:25:17
 */
@Service
public class ApiTeachVideoService{
    @Autowired
    private ApiTeachVideoMapper teachVideoDao;

    public TeachVideoVo queryObject(Long id) {
        return teachVideoDao.queryObject(id);
    }

    public List<TeachVideoVo> queryList(Map<String, Object> map) {
        return teachVideoDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return teachVideoDao.queryTotal(map);
    }

    public int save(TeachVideoVo teachVideo) {
        return teachVideoDao.save(teachVideo);
    }

    public int update(TeachVideoVo teachVideo) {
        return teachVideoDao.update(teachVideo);
    }

    public int delete(Long id) {
        return teachVideoDao.delete(id);
    }

    public int deleteBatch(Long[] ids) {
        return teachVideoDao.deleteBatch(ids);
    }
}
