package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.CommissionOrderDao;
import com.platform.entity.CommissionOrderEntity;
import com.platform.service.CommissionOrderService;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-24 22:02:34
 */
@Service("commissionOrderService")
public class CommissionOrderServiceImpl implements CommissionOrderService {
    @Autowired
    private CommissionOrderDao commissionOrderDao;

    @Override
    public CommissionOrderEntity queryObject(Long id) {
        return commissionOrderDao.queryObject(id);
    }

    @Override
    public List<CommissionOrderEntity> queryList(Map<String, Object> map) {
        return commissionOrderDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return commissionOrderDao.queryTotal(map);
    }

    @Override
    public int save(CommissionOrderEntity commissionOrder) {
        return commissionOrderDao.save(commissionOrder);
    }

    @Override
    public int update(CommissionOrderEntity commissionOrder) {
        return commissionOrderDao.update(commissionOrder);
    }

    @Override
    public int delete(Long id) {
        return commissionOrderDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return commissionOrderDao.deleteBatch(ids);
    }
}
