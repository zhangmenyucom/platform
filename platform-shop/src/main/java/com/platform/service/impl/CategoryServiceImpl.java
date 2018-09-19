package com.platform.service.impl;

import com.platform.dao.CategoryDao;
import com.platform.entity.CategoryEntity;
import com.platform.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-21 15:32:31
 */
@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<CategoryEntity,CategoryDao> implements CategoryService {

    @Override
    public int save(CategoryEntity category) {
        if ("L1".equals(category.getLevel())) {
            category.setParentId(0);
        }
        return getDao().save(category);
    }

    @Override
    public int update(CategoryEntity category) {
        if ("L1".equals(category.getLevel())) {
            category.setParentId(0);
        }
        return getDao().update(category);
    }

    @Override
    @Transactional
    public int deleteBatch(Long[] ids) {
        getDao().deleteByParentBatch(ids);
        return getDao().deleteBatch(ids);
    }
}
