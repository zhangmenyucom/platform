package com.platform.service.impl;

import com.platform.dao.TopicCategoryDao;
import com.platform.entity.TopicCategoryEntity;
import com.platform.service.TopicCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-20 15:41:56
 */
@Service("topicCategoryService")
public class TopicCategoryServiceImpl extends  BaseServiceImpl<TopicCategoryEntity,TopicCategoryDao> implements TopicCategoryService {

}
