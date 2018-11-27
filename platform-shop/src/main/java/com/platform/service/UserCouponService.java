package com.platform.service;

import com.platform.dao.ArticleDao;
import com.platform.entity.ArticleEntity;
import com.platform.entity.UserCouponEntity;
import com.platform.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Service接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-19 15:40:33
 */
public interface UserCouponService extends  BaseService<UserCouponEntity>{

    /**
     * Service接口
     *
     * @author taylor
     * @email 516195940@qq.com
     * @date 2018-11-27 16:34:42
     */
    @Service
    class ArticleService extends BaseServiceImpl<ArticleEntity, ArticleDao> {

    }
}
