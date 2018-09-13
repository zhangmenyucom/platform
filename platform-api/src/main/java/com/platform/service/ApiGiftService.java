package com.platform.service;

import com.platform.dao.ApiGiftMapper;
import com.platform.entity.GiftEntityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-13 15:20:44
 */
@Service
public class ApiGiftService {
    @Autowired
    private ApiGiftMapper giftDaoMapper;

    public GiftEntityVo queryObject(Long id) {
        return giftDaoMapper.queryObject(id);
    }

    public List<GiftEntityVo> queryList(Map<String, Object> map) {
        return giftDaoMapper.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return giftDaoMapper.queryTotal(map);
    }

    public int save(GiftEntityVo gift) {
        return giftDaoMapper.save(gift);
    }

    public int update(GiftEntityVo gift) {
        return giftDaoMapper.update(gift);
    }

    public int delete(Long id) {
        return giftDaoMapper.delete(id);
    }

    public int deleteBatch(Long[] ids) {
        return giftDaoMapper.deleteBatch(ids);
    }
}
