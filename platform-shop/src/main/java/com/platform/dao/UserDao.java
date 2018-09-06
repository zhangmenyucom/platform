package com.platform.dao;

import com.platform.entity.UserEntity;
import com.platform.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * 会员Dao
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-16 15:02:28
 */
public interface UserDao extends BaseDao<UserEntity> {
    List<UserVo> queryDetailInfoList(Map<String, Object> map);

}
