package com.platform.service.impl;

import com.platform.dao.GiftDao;
import com.platform.entity.GiftEntity;
import com.platform.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-12 18:50:18
 */
@Service("giftService")
public class GiftServiceImpl implements GiftService {
    @Autowired
    private GiftDao giftDao;

    @Override
    public GiftEntity queryObject(Long id) {
        return giftDao.queryObject(id);
    }

    @Override
    public List<GiftEntity> queryList(Map<String, Object> map) {
        return giftDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return giftDao.queryTotal(map);
    }

    @Override
    public int save(GiftEntity gift) {
        return giftDao.save(gift);
    }

    @Override
    public int update(GiftEntity gift) {
        return giftDao.update(gift);
    }

    @Override
    public int delete(Long id) {
        return giftDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return giftDao.deleteBatch(ids);
    }
}
