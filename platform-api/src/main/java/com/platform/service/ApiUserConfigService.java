package com.platform.service;

import com.platform.dao.ApiSysUserConfigMapper;
import com.platform.entity.SysUserConfigVo;
import com.platform.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-22 17:58:46
 */
@Service
public class ApiUserConfigService extends BaseServiceImpl<SysUserConfigVo, ApiSysUserConfigMapper> {

    public SysUserConfigVo queryByMerchantId(Long merchantId){
        return getDao().queryByMerchantId(merchantId);
    }

}
