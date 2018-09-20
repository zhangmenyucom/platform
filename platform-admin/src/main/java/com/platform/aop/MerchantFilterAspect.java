package com.platform.aop;

import com.platform.entity.BaseEntity;
import com.platform.entity.SysUserEntity;
import com.platform.utils.Constant;
import com.platform.utils.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 数据过滤，切面处理类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017年10月23日 下午13:33:35
 */
@Aspect
@Component
public class MerchantFilterAspect {
    @Pointcut("@annotation(com.platform.annotation.MerchantFilter)")
    public void merchantFilterCut() {

    }

    /**
     * 前置通知
     *
     * @param point 连接点
     */
    @Before("merchantFilterCut()")
    public void dataFilter(JoinPoint point) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        if (user != null) {
            for (Object params : point.getArgs()) {
                if (user.getUserId() != Constant.SUPER_ADMIN) {
                    if (params instanceof BaseEntity) {
                        BaseEntity baseEntity = (BaseEntity) params;
                        baseEntity.setMerchantId(user.getUserId());
                    }
                    if (params instanceof Map) {
                        Map map = (Map) params;
                        map.put("merchantId", user.getUserId());
                    }
                }
            }
        }
    }
}
