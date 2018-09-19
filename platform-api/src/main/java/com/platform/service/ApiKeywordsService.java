package com.platform.service;

import com.platform.dao.ApiKeywordsMapper;
import com.platform.entity.KeywordsVo;
import com.platform.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiKeywordsService extends BaseServiceImpl<KeywordsVo, ApiKeywordsMapper> {


    public List<Map> hotKeywordList(Map<String, Object> map) {
        return getDao().hotKeywordList(map);
    }
}
