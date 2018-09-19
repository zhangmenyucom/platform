package com.platform.service.impl;

import com.platform.dao.UserLevelDao;
import com.platform.entity.UserLevelEntity;
import com.platform.service.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-16 16:52:22
 */
@Service("userLevelService")
public class UserLevelServiceImpl extends  BaseServiceImpl<UserLevelEntity,UserLevelDao> implements UserLevelService {


}
