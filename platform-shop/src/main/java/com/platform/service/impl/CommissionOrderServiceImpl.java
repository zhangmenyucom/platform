package com.platform.service.impl;

import com.platform.dao.CommissionOrderDao;
import com.platform.entity.CommissionOrderEntity;
import com.platform.service.CommissionOrderService;
import com.platform.utils.Query;
import com.platform.vo.CommissionOrderVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-24 22:02:34
 */
@Service("commissionOrderService")
public class CommissionOrderServiceImpl extends BaseServiceImpl<CommissionOrderEntity, CommissionOrderDao> implements CommissionOrderService {

    @Override
    public List<CommissionOrderVo> queryDetailList(Query query) {
        return getDao().queryDetailList(query);
    }
}
