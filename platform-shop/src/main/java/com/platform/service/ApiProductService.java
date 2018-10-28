package com.platform.service;

import com.platform.dao.ApiProductMapper;
import com.platform.service.impl.BaseServiceImpl;
import com.platform.vo.ProductVo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ApiProductService extends BaseServiceImpl<ProductVo, ApiProductMapper> {

    public List<ProductVo> queryProductByGoodsId(Long goodsId) {
        return this.getDao().queryProductByGoodsId(goodsId);
    }
}
