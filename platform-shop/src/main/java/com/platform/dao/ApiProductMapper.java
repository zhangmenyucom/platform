package com.platform.dao;

import com.platform.vo.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-11 09:16:46
 */
public interface ApiProductMapper extends BaseDao<ProductVo> {

    List<ProductVo> queryProductByGoodsId(@Param("goodsId") Long goodsId);
}
