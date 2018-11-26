package com.platform.service;


import com.platform.dao.ApiNewGiftRecordMapper;
import com.platform.service.impl.BaseServiceImpl;
import com.platform.vo.NewGiftRecordVo;
import org.springframework.stereotype.Service;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-10-11 19:05:18
 */
@Service
public class ApiNewGiftRecordService extends BaseServiceImpl<NewGiftRecordVo, ApiNewGiftRecordMapper> {

    public NewGiftRecordVo queryByUserId(Long userId) {
        return this.getDao().queryByUserId(userId);
    }
}
