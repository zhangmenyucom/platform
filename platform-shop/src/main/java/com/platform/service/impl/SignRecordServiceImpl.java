package com.platform.service.impl;

import com.platform.dao.SignRecordDao;
import com.platform.entity.SignRecordEntity;
import com.platform.service.SignRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-09 21:48:06
 */
@Service("signRecordService")
public class SignRecordServiceImpl extends BaseServiceImpl<SignRecordEntity,SignRecordDao> implements SignRecordService {

}
