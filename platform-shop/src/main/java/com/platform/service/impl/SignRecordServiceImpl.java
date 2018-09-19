package com.platform.service.impl;

import com.platform.dao.SignRecordDao;
import com.platform.entity.SignRecordEntity;
import com.platform.service.SignRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-09 21:48:06
 */
@Service("signRecordService")
public class SignRecordServiceImpl implements SignRecordService {
    @Autowired
    private SignRecordDao signRecordDao;

    @Override
    public SignRecordEntity queryObject(Long id) {
        return signRecordDao.queryObject(id);
    }

    @Override
    public List<SignRecordEntity> queryList(Map<String, Object> map) {
        return signRecordDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return signRecordDao.queryTotal(map);
    }

    @Override
    public int save(SignRecordEntity signRecord) {
        return signRecordDao.save(signRecord);
    }

    @Override
    public int update(SignRecordEntity signRecord) {
        return signRecordDao.update(signRecord);
    }

    @Override
    public int delete(Long id) {
        return signRecordDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return signRecordDao.deleteBatch(ids);
    }
}
