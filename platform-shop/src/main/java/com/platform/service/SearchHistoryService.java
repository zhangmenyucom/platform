package com.platform.service;

import com.platform.entity.SearchHistoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:10
 */
public interface SearchHistoryService {
	
	SearchHistoryEntity queryObject(Long id);
	
	List<SearchHistoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SearchHistoryEntity searchHistory);
	
	void update(SearchHistoryEntity searchHistory);
	
	void delete(Long id);
	
	void deleteBatch(Integer[] ids);
}
