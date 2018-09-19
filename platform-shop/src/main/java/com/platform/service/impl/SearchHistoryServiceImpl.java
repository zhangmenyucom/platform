package com.platform.service.impl;

import com.platform.dao.SearchHistoryDao;
import com.platform.entity.SearchHistoryEntity;
import com.platform.service.SearchHistoryService;
import org.springframework.stereotype.Service;


@Service("searchHistoryService")
public class SearchHistoryServiceImpl extends BaseServiceImpl<SearchHistoryEntity,SearchHistoryDao> implements SearchHistoryService {
	
}
