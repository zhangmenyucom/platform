package com.platform.service.impl;

import com.platform.dao.ArticleDao;
import com.platform.entity.ArticleEntity;
import com.platform.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-22 00:14:02
 */
@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl<ArticleEntity,ArticleDao> implements ArticleService {

}
