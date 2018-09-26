package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.platform.dao.SysConfigDao;
import com.platform.entity.SysConfigEntity;
import com.platform.service.SysConfigService;
import com.platform.utils.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


/**
 * @author Taylor
 */
@Service("sysConfigService")
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfigEntity, SysConfigDao> implements SysConfigService {

    @Override
    public void updateValueByKey(String key, String value) {
        this.getDao().updateValueByKey(key, value);
    }


    @Override
    public String getValue(String key, String defaultValue) {
        String value = this.getDao().queryByKey(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value;
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key, null);
        if (StringUtils.isNotBlank(value)) {
            return JSON.parseObject(value, clazz);
        }
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RRException("获取参数失败");
        }
    }
}
