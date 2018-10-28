package com.platform.service;

import com.platform.entity.WithdrawOrderEntity;

/**
 * Service接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-24 22:02:34
 */
public interface WithdrawOrderService extends BaseService<WithdrawOrderEntity> {

    void auditingWithdrawOrder(WithdrawOrderEntity withdrawOrder);

}
