package com.platform.dao;

import com.platform.entity.CommissionOrderVo;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-25 14:20:46
 */
public interface ApiCommissionOrderMapper extends BaseDao<CommissionOrderVo> {

    List<CommissionOrderVo> queryDetailList(Map<String, Object> map);

}
