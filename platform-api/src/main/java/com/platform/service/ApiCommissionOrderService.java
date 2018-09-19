package com.platform.service;

import com.platform.dao.ApiCommissionOrderMapper;
import com.platform.entity.CommissionOrderVo;
import com.platform.service.impl.BaseServiceImpl;
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
public class ApiCommissionOrderService extends BaseServiceImpl<CommissionOrderVo, ApiCommissionOrderMapper> {
    public List<CommissionOrderVo> queryDetailList(Map<String, Object> map) {
        return getDao().queryDetailList(map);
    }

}
