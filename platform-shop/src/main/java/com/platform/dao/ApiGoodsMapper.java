package com.platform.dao;


import com.platform.vo.GoodsVo;

import java.util.List;
import java.util.Map;

/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-11 09:16:45
 */
public interface ApiGoodsMapper extends BaseDao<GoodsVo> {

    List<GoodsVo> queryHotGoodsList(Map<String, Object> params);

    List<GoodsVo> queryCatalogProductList(Map<String, Object> params);
}
