package com.platform.service.impl;

import com.platform.dao.SysUserConfigDao;
import com.platform.entity.SysUserConfigEntity;
import com.platform.service.SysUserConfigService;
import com.platform.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-22 17:58:46
 */
@Service("sysUserConfigService")
public class SysUserConfigServiceImpl extends BaseServiceImpl<SysUserConfigEntity,SysUserConfigDao> implements SysUserConfigService {

    @Override
    public List<SysUserConfigEntity> queryAll(Query query) {
        return this.getDao().queryAll(query);
    }

    @Override
    public int queryTotalAll(Query query) {
        return this.getDao().queryTotalAll(query);
    }
}
