package com.platform.service.impl;

import com.platform.dao.WithdrawOrderDao;
import com.platform.entity.WithdrawOrderEntity;
import com.platform.service.WithdrawOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-24 22:02:34
 */
@Service("withdrawOrderService")
public class WithdrawOrderServiceImpl extends BaseServiceImpl<WithdrawOrderEntity,WithdrawOrderDao> implements WithdrawOrderService {

}
