package com.platform.service;

import com.platform.dao.ApiCommentMapper;
import com.platform.service.impl.BaseServiceImpl;
import com.platform.vo.CommentVo;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class ApiCommentService extends BaseServiceImpl<CommentVo,ApiCommentMapper> {
    public int queryhasPicTotal(Map<String, Object> map,Long merchantId) {
        map.put("merchantId",merchantId);
        return getDao().queryhasPicTotal(map);
    }

}