package com.platform.service.impl;

import com.platform.dao.FeedbackDao;
import com.platform.entity.FeedbackEntity;
import com.platform.service.FeedbackService;
import org.springframework.stereotype.Service;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-23 15:03:25
 */
@Service("feedbackService")
public class FeedbackServiceImpl extends BaseServiceImpl<FeedbackEntity,FeedbackDao> implements FeedbackService {

}
