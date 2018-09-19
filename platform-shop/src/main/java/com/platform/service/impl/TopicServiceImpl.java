package com.platform.service.impl;

import com.platform.dao.TopicDao;
import com.platform.entity.TopicEntity;
import com.platform.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-20 14:10:08
 */
@Service("topicService")
public class TopicServiceImpl extends BaseServiceImpl<TopicEntity,TopicDao> implements TopicService {

}
