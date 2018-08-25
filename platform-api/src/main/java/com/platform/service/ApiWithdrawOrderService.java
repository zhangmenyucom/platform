package com.platform.service;

import com.platform.dao.ApiWithdrawOrderDao;
import com.platform.entity.WithdrawOrderVo;
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
public class ApiWithdrawOrderService {

    @Autowired
    private ApiWithdrawOrderDao withdrawOrderDao;

    public WithdrawOrderVo queryObject(Long id) {
        return withdrawOrderDao.queryObject(id);
    }

    public List<WithdrawOrderVo> queryList(Map<String, Object> map) {
        return withdrawOrderDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return withdrawOrderDao.queryTotal(map);
    }

    public int save(WithdrawOrderVo withdrawOrder) {
        return withdrawOrderDao.save(withdrawOrder);
    }

    public int update(WithdrawOrderVo withdrawOrder) {
        return withdrawOrderDao.update(withdrawOrder);
    }

    public int delete(Long id) {
        return withdrawOrderDao.delete(id);
    }

    public int deleteBatch(Long[] ids) {
        return withdrawOrderDao.deleteBatch(ids);
    }
}
