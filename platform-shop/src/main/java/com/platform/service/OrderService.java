package com.platform.service;

import com.platform.entity.OrderEntity;

/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:09
 */
public interface OrderService  extends  BaseService<OrderEntity>{
    /**
     * 确定收货
     *
     * @param id
     * @return
     */
    int confirm(Long id);

    int sendGoods(OrderEntity order);
}
