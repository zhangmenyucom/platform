package com.platform.dao;

import com.platform.entity.OrderVo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-11 09:16:46
 */
public interface ApiOrderMapper extends BaseDao<OrderVo> {

    OrderVo queryByOrderSn(@Param("orderSn") String orderSn);
}
