package com.platform.service;

import com.platform.entity.GoodsEntity;

/**
 * Service接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-21 21:19:49
 */
public interface GoodsService extends BaseService<GoodsEntity> {

    /**
     * 商品从回收站恢复
     *
     * @param ids
     * @return
     */
    int back(Long[] ids);

    /**
     * 上架
     *
     * @param id
     * @return
     */
    int enSale(Long id);

    /**
     * 下架
     *
     * @param id
     * @return
     */
    int unSale(Long id);
}
