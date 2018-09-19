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
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public ArticleEntity queryObject(Long id) {
        return articleDao.queryObject(id);
    }

    @Override
    public List<ArticleEntity> queryList(Map<String, Object> map) {
        return articleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return articleDao.queryTotal(map);
    }

    @Override
    public int save(ArticleEntity article) {
        return articleDao.save(article);
    }

    @Override
    public int update(ArticleEntity article) {
        return articleDao.update(article);
    }

    @Override
    public int delete(Long id) {
        return articleDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return articleDao.deleteBatch(ids);
    }
}
