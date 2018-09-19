package com.platform.service.impl;

import com.platform.dao.UserDao;
import com.platform.entity.UserEntity;
import com.platform.service.UserService;
import com.platform.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-16 15:02:28
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserEntity, UserDao> implements UserService {

    @Override
    public List<UserVo> queryDetailInfoList(Map<String, Object> map) {
        return getDao().queryDetailInfoList(map);
    }
}
