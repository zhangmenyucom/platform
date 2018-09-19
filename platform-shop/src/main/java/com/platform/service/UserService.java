package com.platform.service;

import com.platform.entity.UserEntity;
import com.platform.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-16 15:02:28
 */
public interface UserService extends BaseService<UserEntity> {
    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<UserVo> queryDetailInfoList(Map<String, Object> map);

}
