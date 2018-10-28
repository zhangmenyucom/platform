package com.platform.service;

import com.platform.dao.ApiFootprintMapper;
import com.platform.service.impl.BaseServiceImpl;
import com.platform.vo.FootprintVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiFootprintService extends BaseServiceImpl<FootprintVo, ApiFootprintMapper> {

    public List<FootprintVo> queryListFootprint(String userid) {
        return getDao().queryListFootprint(userid);
    }

    public void deleteByParam(Map<String, Object> map) {
        getDao().deleteByParam(map);
    }

    public List<FootprintVo> shareList(Map<String, Object> map) {
        return getDao().shareList(map);
    }
}
