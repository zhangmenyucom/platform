package com.platform.service.impl;

import com.platform.annotation.MerchantFilter;
import com.platform.dao.OrderDao;
import com.platform.dao.ShippingDao;
import com.platform.entity.OrderEntity;
import com.platform.entity.ShippingEntity;
import com.platform.service.OrderService;
import com.platform.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Taylor
 */
@Service
public class OrderServiceImpl extends  BaseServiceImpl<OrderEntity,OrderDao> implements OrderService {

    @Autowired
    private ShippingDao shippingDao;

    @Override
    public int confirm(Long id) {
        OrderEntity orderEntity = queryObject(id);
        //发货状态
        Integer shippingStatus = orderEntity.getShippingStatus();
        //付款状态
        Integer payStatus = orderEntity.getPayStatus();
        if (2 != payStatus) {
            throw new RRException("此订单未付款，不能确认收货！");
        }
        if (4 == shippingStatus) {
            throw new RRException("此订单处于退货状态，不能确认收货！");
        }
        if (0 == shippingStatus) {
            throw new RRException("此订单未发货，不能确认收货！");
        }
        orderEntity.setOrderStatus(301);
        orderEntity.setShippingStatus(2);
        return getDao().update(orderEntity);
    }

    @Override
    @MerchantFilter
    public int sendGoods(OrderEntity order) {
        //付款状态
        Integer payStatus = order.getPayStatus();
        if (2 != payStatus) {
            throw new RRException("此订单未付款！");
        }

        ShippingEntity shippingEntity = shippingDao.queryObject(order.getShippingId());
        if (null != shippingEntity) {
            order.setShippingName(shippingEntity.getName());
        }
        //订单已发货
        order.setOrderStatus(300);
        //已发货
        order.setShippingStatus(1);
        return getDao().update(order);
    }
}
