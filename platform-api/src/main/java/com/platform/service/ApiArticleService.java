package com.platform.service;

import com.platform.dao.ApiArticleDao;
import com.platform.entity.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-22 00:14:02
 */
@Service
public class ApiArticleService {

    @Autowired
    private ApiArticleDao apiArticleDao;

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    public ArticleVo queryObject(Long id) {
        return apiArticleDao.queryObject(id);
    }

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    public List<ArticleVo> queryList(Map<String, Object> map) {
        return apiArticleDao.queryList(map);
    }

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    public int queryTotal(Map<String, Object> map) {
        return apiArticleDao.queryTotal(map);
    }

    /**
     * 保存实体
     *
     * @param article 实体
     * @return 保存条数
     */
    public int save(ArticleVo article) {
        return apiArticleDao.save(article);
    }

    /**
     * 根据主键更新实体
     *
     * @param article 实体
     * @return 更新条数
     */
    public int update(ArticleVo article) {
        return apiArticleDao.update(article);
    }

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    public int delete(Long id) {
        return apiArticleDao.delete(id);
    }

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    public int deleteBatch(Long[] ids) {
        return apiArticleDao.deleteBatch(ids);
    }
}
