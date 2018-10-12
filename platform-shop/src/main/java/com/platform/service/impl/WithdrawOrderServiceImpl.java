package com.platform.service.impl;

import com.platform.annotation.MerchantFilter;
import com.platform.dao.WithdrawOrderDao;
import com.platform.entity.EnterpriceToCustomerEntity;
import com.platform.entity.TransferReqBean;
import com.platform.entity.UserEntity;
import com.platform.entity.WithdrawOrderEntity;
import com.platform.service.UserService;
import com.platform.service.WithdrawOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class WithdrawOrderServiceImpl extends BaseServiceImpl<WithdrawOrderEntity, WithdrawOrderDao> implements WithdrawOrderService {
    @Autowired
    private TransferService transferService;

    @Autowired
    private UserService userService;

    @Override
    @MerchantFilter
    public void auditingWithdrawOrder(WithdrawOrderEntity withdrawOrder) {
        WithdrawOrderEntity withdrawOrderEntity = this.getDao().queryObject(withdrawOrder.getId());
        withdrawOrderEntity.setStatus(withdrawOrder.getStatus());
        withdrawOrderEntity.setComment(withdrawOrder.getComment());
        UserEntity userEntity = userService.queryObject(withdrawOrder.getUserId());
        if (withdrawOrder.getStatus() == 1) {
            this.getDao().update(withdrawOrderEntity);
            TransferReqBean transferReqBean = new TransferReqBean();
            transferReqBean.setWithdrawOrderId(withdrawOrder.getId());
            transferReqBean.setAmount(withdrawOrderEntity.getWithdrawAmount().multiply(BigDecimal.valueOf(100)).intValue());
            transferReqBean.setDesc("佣金提现");
            transferReqBean.setOpenId(userEntity.getWeixinOpenid());
            transferReqBean.setRealName(withdrawOrderEntity.getRealName());
            transferReqBean.setNeedCheckName(true);
            transferReqBean.setMerchantId(withdrawOrder.getMerchantId());
            EnterpriceToCustomerEntity etoc = transferService.payToCustom(transferReqBean);
            if ("SUCCESS".equalsIgnoreCase(etoc.getResult_code())) {
                withdrawOrderEntity.setStatus(3);
                withdrawOrderEntity.setOrderSn(etoc.getPayment_no());
                withdrawOrderEntity.setComment(withdrawOrder.getComment() + "\n" + etoc.getReturn_msg());
                //减去锁定金额
                userEntity.setLockBalance(userEntity.getLockBalance().subtract(withdrawOrderEntity.getWithdrawAmount()));
                userEntity.setTotalBalance(userEntity.getTotalBalance().subtract(withdrawOrderEntity.getWithdrawAmount()));
                userService.update(userEntity);
            } else {
                withdrawOrderEntity.setStatus(4);
                //还原锁定金额
                userEntity.setLockBalance(userEntity.getLockBalance().subtract(withdrawOrderEntity.getWithdrawAmount()));
                userEntity.setAvilableBalance(userEntity.getAvilableBalance().add(withdrawOrderEntity.getWithdrawAmount()));
                userService.update(userEntity);
                withdrawOrderEntity.setComment(withdrawOrderEntity.getComment() + "\n" + etoc.getErr_code_des());
            }
            this.getDao().update(withdrawOrderEntity);
        } else {
            this.getDao().update(withdrawOrderEntity);
        }
    }
}
