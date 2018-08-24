package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.CmmissionOrderDao;
import com.platform.entity.CmmissionOrderEntity;
import com.platform.service.CmmissionOrderService;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-24 22:02:34
 */
@Service("cmmissionOrderService")
public class CmmissionOrderServiceImpl implements CmmissionOrderService {
    @Autowired
    private CmmissionOrderDao cmmissionOrderDao;

    @Override
    public CmmissionOrderEntity queryObject(Long id) {
        return cmmissionOrderDao.queryObject(id);
    }

    @Override
    public List<CmmissionOrderEntity> queryList(Map<String, Object> map) {
        return cmmissionOrderDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return cmmissionOrderDao.queryTotal(map);
    }

    @Override
    public int save(CmmissionOrderEntity cmmissionOrder) {
        return cmmissionOrderDao.save(cmmissionOrder);
    }

    @Override
    public int update(CmmissionOrderEntity cmmissionOrder) {
        return cmmissionOrderDao.update(cmmissionOrder);
    }

    @Override
    public int delete(Long id) {
        return cmmissionOrderDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return cmmissionOrderDao.deleteBatch(ids);
    }
}
