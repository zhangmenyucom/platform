package com.platform.service;/**
 * ${author} on 2018/9/19.
 */

import java.util.List;
import java.util.Map;

/**
 * @author zhangxiaolu
 * @描述 通用服务接口
 * @since 2018/9/19 11:36
 */
public interface BaseService<T> {
    T queryObject(Long id);

    List<T> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(T t);

    void update(T t);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
