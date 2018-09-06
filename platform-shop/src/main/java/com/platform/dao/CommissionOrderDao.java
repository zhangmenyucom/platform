package com.platform.dao;

import com.platform.entity.CommissionOrderEntity;
import com.platform.vo.CommissionOrderVo;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-24 22:02:34
 */
public interface CommissionOrderDao extends BaseDao<CommissionOrderEntity> {

    List<CommissionOrderVo> queryDetailList(Map<String, Object> map);

}
