package com.platform.dao;

import com.platform.entity.SysUserConfigEntity;
import com.platform.utils.Query;

import java.util.List;

/**
 * Dao
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-22 17:58:46
 */
public interface SysUserConfigDao extends BaseDao<SysUserConfigEntity> {
    SysUserConfigEntity queryByMerchantId(Long merchantId);

    List<SysUserConfigEntity> queryAll(Query query);

    int queryTotalAll(Query query);
}
