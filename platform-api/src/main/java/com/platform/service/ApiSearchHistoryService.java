package com.platform.service;

import com.platform.dao.ApiSearchHistoryMapper;
import com.platform.entity.SearchHistoryVo;
import com.platform.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class ApiSearchHistoryService extends BaseServiceImpl<SearchHistoryVo, ApiSearchHistoryMapper> {
    public void deleteByUserId(Long userId) {
        getDao().deleteByUserId(userId);
    }
}
