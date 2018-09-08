package com.platform.service;

import com.platform.entity.SmsLogEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-09 04:53:06
 */
public interface SmsLogService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    SmsLogEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<SmsLogEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param smsLog 实体
     * @return 保存条数
     */
    int save(SmsLogEntity smsLog);

    /**
     * 根据主键更新实体
     *
     * @param smsLog 实体
     * @return 更新条数
     */
    int update(SmsLogEntity smsLog);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);
}
