package com.platform.service;

import com.platform.entity.SysUserConfigEntity;
import com.platform.utils.Query;

import java.util.List;


/**
 * Service接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-22 17:58:46
 */
public interface SysUserConfigService extends BaseService<SysUserConfigEntity> {

    List<SysUserConfigEntity> queryAll(Query query);

    int queryTotalAll(Query query);

}
