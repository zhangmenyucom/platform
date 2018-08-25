package com.platform.service;

import com.platform.dao.ApiCommissionOrderDao;
import com.platform.entity.CommissionOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-25 14:20:46
 */
@Service
public class ApiCommissionOrderService {

    @Autowired
    private ApiCommissionOrderDao commissionOrderDao;

    public CommissionOrderVo queryObject(Long id) {
        return commissionOrderDao.queryObject(id);
    }

    public List<CommissionOrderVo> queryList(Map<String, Object> map) {
        return commissionOrderDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return commissionOrderDao.queryTotal(map);
    }

    public int save(CommissionOrderVo commissionOrder) {
        return commissionOrderDao.save(commissionOrder);
    }

    public int update(CommissionOrderVo commissionOrder) {
        return commissionOrderDao.update(commissionOrder);
    }

    public int delete(Long id) {
        return commissionOrderDao.delete(id);
    }

    public int deleteBatch(Long[] ids) {
        return commissionOrderDao.deleteBatch(ids);
    }
}
