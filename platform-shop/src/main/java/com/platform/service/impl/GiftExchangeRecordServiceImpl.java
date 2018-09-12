package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.GiftExchangeRecordDao;
import com.platform.entity.GiftExchangeRecordEntity;
import com.platform.service.GiftExchangeRecordService;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-12 18:50:18
 */
@Service("giftExchangeRecordService")
public class GiftExchangeRecordServiceImpl implements GiftExchangeRecordService {
    @Autowired
    private GiftExchangeRecordDao giftExchangeRecordDao;

    @Override
    public GiftExchangeRecordEntity queryObject(Long id) {
        return giftExchangeRecordDao.queryObject(id);
    }

    @Override
    public List<GiftExchangeRecordEntity> queryList(Map<String, Object> map) {
        return giftExchangeRecordDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return giftExchangeRecordDao.queryTotal(map);
    }

    @Override
    public int save(GiftExchangeRecordEntity giftExchangeRecord) {
        return giftExchangeRecordDao.save(giftExchangeRecord);
    }

    @Override
    public int update(GiftExchangeRecordEntity giftExchangeRecord) {
        return giftExchangeRecordDao.update(giftExchangeRecord);
    }

    @Override
    public int delete(Long id) {
        return giftExchangeRecordDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return giftExchangeRecordDao.deleteBatch(ids);
    }
}
