package com.platform.dao;


import com.platform.vo.SysUserConfigVo;

/**
 * Dao
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-22 17:58:46
 */
public interface ApiSysUserConfigMapper extends BaseDao<SysUserConfigVo> {
    SysUserConfigVo queryByMerchantId(Long merchantId);
}
