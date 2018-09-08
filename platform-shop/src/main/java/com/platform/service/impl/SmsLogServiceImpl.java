package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.SmsLogDao;
import com.platform.entity.SmsLogEntity;
import com.platform.service.SmsLogService;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-09 04:53:06
 */
@Service
public class SmsLogServiceImpl implements SmsLogService {
    @Autowired
    private SmsLogDao smsLogDao;

    @Override
    public SmsLogEntity queryObject(Integer id) {
        return smsLogDao.queryObject(id);
    }

    @Override
    public List<SmsLogEntity> queryList(Map<String, Object> map) {
        return smsLogDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return smsLogDao.queryTotal(map);
    }

    @Override
    public int save(SmsLogEntity smsLog) {
        return smsLogDao.save(smsLog);
    }

    @Override
    public int update(SmsLogEntity smsLog) {
        return smsLogDao.update(smsLog);
    }

    @Override
    public int delete(Integer id) {
        return smsLogDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return smsLogDao.deleteBatch(ids);
    }
}
