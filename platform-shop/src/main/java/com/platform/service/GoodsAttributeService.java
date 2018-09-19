package com.platform.service;

import com.platform.entity.GoodsAttributeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:08
 */
public interface GoodsAttributeService {
	
	GoodsAttributeEntity queryObject(Long id);
	
	List<GoodsAttributeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GoodsAttributeEntity goodsAttribute);
	
	void update(GoodsAttributeEntity goodsAttribute);
	
	void delete(Long id);
	
	void deleteBatch(Integer[] ids);
}
