package com.platform.service;

import com.platform.entity.SysConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2016年12月4日 下午6:49:01
 */
public interface SysConfigService extends BaseService<SysConfigEntity> {

    /**
     * 根据key，更新value
     */
     void updateValueByKey(String key, String value);

    /**
     * 根据key，获取配置的value值
     *
     * @param key          key
     * @param defaultValue 缺省值
     */
    String getValue(String key, String defaultValue);

    /**
     * 根据key，获取value的Object对象
     *
     * @param key   key
     * @param clazz Object对象
     */
    <T> T getConfigObject(String key, Class<T> clazz);

}
