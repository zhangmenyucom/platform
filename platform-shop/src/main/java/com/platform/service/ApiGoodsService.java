package com.platform.service;

import com.platform.dao.ApiGoodsMapper;
import com.platform.service.impl.BaseServiceImpl;
import com.platform.vo.GoodsVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiGoodsService extends BaseServiceImpl<GoodsVo, ApiGoodsMapper> {

    public List<GoodsVo> queryHotGoodsList(Map<String, Object> map) {
        return getDao().queryHotGoodsList(map);
    }

    public List<GoodsVo> queryCatalogProductList(Map<String, Object> map) {
        return getDao().queryCatalogProductList(map);
    }
}
