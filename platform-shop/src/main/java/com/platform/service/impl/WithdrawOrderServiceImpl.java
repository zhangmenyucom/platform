package com.platform.service.impl;

import com.platform.dao.WithdrawOrderDao;
import com.platform.entity.WithdrawOrderEntity;
import com.platform.service.WithdrawOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-24 22:02:34
 */
@Service("withdrawOrderService")
public class WithdrawOrderServiceImpl implements WithdrawOrderService {
    @Autowired
    private WithdrawOrderDao withdrawOrderDao;

    @Override
    public WithdrawOrderEntity queryObject(Long id) {
        return withdrawOrderDao.queryObject(id);
    }

    @Override
    public List<WithdrawOrderEntity> queryList(Map<String, Object> map) {
        return withdrawOrderDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return withdrawOrderDao.queryTotal(map);
    }

    @Override
    public int save(WithdrawOrderEntity withdrawOrder) {
        return withdrawOrderDao.save(withdrawOrder);
    }

    @Override
    public int update(WithdrawOrderEntity withdrawOrder) {
        return withdrawOrderDao.update(withdrawOrder);
    }

    @Override
    public int delete(Long id) {
        return withdrawOrderDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return withdrawOrderDao.deleteBatch(ids);
    }
}
