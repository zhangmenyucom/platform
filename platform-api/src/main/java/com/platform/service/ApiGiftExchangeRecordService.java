package com.platform.service;

import com.platform.dao.ApiGiftExchangeRecordMapper;
import com.platform.entity.GiftExchangeRecordEntityVo;
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
public class ApiGiftExchangeRecordService {

    private ApiGiftExchangeRecordMapper giftExchangeRecordMapper;

    public GiftExchangeRecordEntityVo queryObject(Long id) {
        return giftExchangeRecordMapper.queryObject(id);
    }

    public List<GiftExchangeRecordEntityVo> queryList(Map<String, Object> map) {
        return giftExchangeRecordMapper.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return giftExchangeRecordMapper.queryTotal(map);
    }

    public int save(GiftExchangeRecordEntityVo giftExchangeRecord) {
        return giftExchangeRecordMapper.save(giftExchangeRecord);
    }

    public int update(GiftExchangeRecordEntityVo giftExchangeRecord) {
        return giftExchangeRecordMapper.update(giftExchangeRecord);
    }

    public int delete(Long id) {
        return giftExchangeRecordMapper.delete(id);
    }

    public int deleteBatch(Long[] ids) {
        return giftExchangeRecordMapper.deleteBatch(ids);
    }
}
