package com.platform.service;

import com.platform.dao.ApiSysUserConfigMapper;
import com.platform.entity.SysUserConfigVo;
import com.platform.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.SysUserConfigDao;
import com.platform.entity.SysUserConfigEntity;
import com.platform.service.SysUserConfigService;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-22 17:58:46
 */
@Service
public class ApiUserConfigService extends BaseServiceImpl<SysUserConfigVo, ApiSysUserConfigMapper> {

}
