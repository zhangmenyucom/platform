package com.platform.service.impl;
/**
 * ${author} on 2018/9/19.
 */

import com.platform.annotation.MerchantFilter;
import com.platform.dao.BaseDao;
import com.platform.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/9/19 11:42
 */
public abstract class  BaseServiceImpl<T ,Dao extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    private Dao dao;

    public Dao getDao() {
        return this.dao;
    }

    @Override
    public T queryObject(Long id) {
        return this.dao.queryObject(id);
    }

    @Override
    @MerchantFilter
    public List<T> queryList(Map<String, Object> map) {
        return this.dao.queryList(map);
    }

    @Override
    @MerchantFilter
    public int queryTotal(Map<String, Object> map) {
        return this.dao.queryTotal(map);
    }

    @Override
    @MerchantFilter
    public int save(T t) {
        return  this.dao.save(t);
    }

    @Override
    @MerchantFilter
    public int update(T t) {
        return this.dao.update(t);
    }

    @Override
    public int delete(Long id) {
        return this.dao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
       return  this.dao.deleteBatch(ids);
    }
}
