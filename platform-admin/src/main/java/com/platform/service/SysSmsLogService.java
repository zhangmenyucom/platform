package com.platform.service;

import com.platform.entity.SysSmsLogEntity;

/**
 * 发送短信日志Service
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-12-16 23:38:05
 */
public interface SysSmsLogService extends BaseService<SysSmsLogEntity>  {

    SysSmsLogEntity sendSms(SysSmsLogEntity smsLog);
}
