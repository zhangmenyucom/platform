package com.platform.service.impl;

import com.platform.dao.SearchHistoryDao;
import com.platform.entity.SearchHistoryEntity;
import com.platform.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("searchHistoryService")
public class SearchHistoryServiceImpl extends BaseServiceImpl<SearchHistoryEntity,SearchHistoryDao> implements SearchHistoryService {
	
}
