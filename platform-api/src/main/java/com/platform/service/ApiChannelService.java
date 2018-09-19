package com.platform.service;

import com.platform.dao.ApiChannelMapper;
import com.platform.entity.ChannelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiChannelService {
    @Autowired
    private ApiChannelMapper channelDao;


    public ChannelVo queryObject(Long id) {
        return channelDao.queryObject(id);
    }


    public List<ChannelVo> queryList(Map<String, Object> map) {
        return channelDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return channelDao.queryTotal(map);
    }


    public void save(ChannelVo order) {
        channelDao.save(order);
    }


    public void update(ChannelVo order) {
        channelDao.update(order);
    }


    public void delete(Long id) {
        channelDao.delete(id);
    }


    public void deleteBatch(Long[] ids) {
        channelDao.deleteBatch(ids);
    }

}
