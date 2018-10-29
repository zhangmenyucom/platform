package com.platform.service;

import com.platform.dao.ApiFeedbackMapper;
import com.platform.service.impl.BaseServiceImpl;
import com.platform.vo.FeedbackVo;
import org.springframework.stereotype.Service;


@Service
public class ApiFeedbackService extends BaseServiceImpl<FeedbackVo, ApiFeedbackMapper> {

}
