package com.platform.service;

import com.platform.dao.ApiTopicMapper;
import com.platform.service.impl.BaseServiceImpl;
import com.platform.vo.TopicVo;
import org.springframework.stereotype.Service;


@Service
public class ApiTopicService  extends BaseServiceImpl<TopicVo, ApiTopicMapper> {

}
