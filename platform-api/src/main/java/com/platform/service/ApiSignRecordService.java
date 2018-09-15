package com.platform.service;

import com.platform.dao.ApiSignRecordMapper;
import com.platform.entity.SignRecordVo;
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
public class ApiSignRecordService {
    @Autowired
    private ApiSignRecordMapper signRecordDao;

    public SignRecordVo queryObject(Long id) {
        return signRecordDao.queryObject(id);
    }

    public SignRecordVo queryLatestSign(Long userId){
        return signRecordDao.queryLatestSign(userId);
    }

    public List<SignRecordVo> queryList(Map<String, Object> map) {
        return signRecordDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return signRecordDao.queryTotal(map);
    }

    public int save(SignRecordVo signRecord) {
        return signRecordDao.save(signRecord);
    }

    public int update(SignRecordVo signRecord) {
        return signRecordDao.update(signRecord);
    }

    public int delete(Long id) {
        return signRecordDao.delete(id);
    }

    public int deleteBatch(Long[] ids) {
        return signRecordDao.deleteBatch(ids);
    }
}
