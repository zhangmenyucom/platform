package com.platform.service;

import com.platform.dao.ApiSignRecordMapper;
import com.platform.entity.SignRecordVo;
import com.platform.service.impl.BaseServiceImpl;
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
@Service
public class ApiSignRecordService extends BaseServiceImpl<SignRecordVo,ApiSignRecordMapper> {

    public SignRecordVo queryLatestSign(Long userId){
        return getDao().queryLatestSign(userId);
    }
}
