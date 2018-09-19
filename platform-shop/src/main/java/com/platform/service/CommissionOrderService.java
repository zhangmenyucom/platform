package com.platform.service;

import com.platform.entity.CommissionOrderEntity;
import com.platform.utils.Query;
import com.platform.vo.CommissionOrderVo;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-24 22:02:34
 */
public interface CommissionOrderService extends  BaseService<CommissionOrderEntity> {

    List<CommissionOrderVo> queryDetailList(Query query);

}
