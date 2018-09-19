package com.platform.aop;

import com.platform.entity.BaseEntity;
import com.platform.entity.SysUserEntity;
import com.platform.utils.Constant;
import com.platform.utils.RRException;
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
        //获取参数
        Object params = point.getArgs()[0];
        if (params != null) {
            SysUserEntity user = ShiroUtils.getUserEntity();
            //在登录时没有user
            if (user != null) {
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
            return;
        }
        throw new RRException("数据权限接口的参数必须为Map或BaseEntity类型，且不能为NULL");
    }
}
