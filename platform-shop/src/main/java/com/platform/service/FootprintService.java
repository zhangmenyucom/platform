package com.platform.service;

import com.platform.entity.FootprintEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:08
 */
public interface FootprintService {
	
	FootprintEntity queryObject(Long id);
	
	List<FootprintEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(FootprintEntity footprint);
	
	void update(FootprintEntity footprint);
	
	void delete(Long id);
	
	void deleteBatch(Integer[] ids);
}
