package com.platform.service.impl;

import com.platform.dao.KeywordsDao;
import com.platform.entity.KeywordsEntity;
import com.platform.service.KeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 热闹关键词表Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-25 21:23:41
 */
@Service("keywordsService")
public class KeywordsServiceImpl extends BaseServiceImpl<KeywordsEntity,KeywordsDao> implements KeywordsService {
}
