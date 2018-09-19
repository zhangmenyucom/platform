package com.platform.service;

import com.platform.dao.ApiBrandMapper;
import com.platform.entity.BrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiBrandService {
	@Autowired
	private ApiBrandMapper brandDao;
	
	
	public BrandVo queryObject(Long id){
		return brandDao.queryObject(id);
	}
	
	
	public List<BrandVo> queryList(Map<String, Object> map){
		return brandDao.queryList(map);
	}
	
	
	public int queryTotal(Map<String, Object> map){
		return brandDao.queryTotal(map);
	}
	
	
	public void save(BrandVo brand){
		brandDao.save(brand);
	}
	
	
	public void update(BrandVo brand){
		brandDao.update(brand);
	}
	
	
	public void delete(Long id){
		brandDao.delete(id);
	}
	
	
	public void deleteBatch(Long[] ids){
		brandDao.deleteBatch(ids);
	}
	
}
