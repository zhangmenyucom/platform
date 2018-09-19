package com.platform.service;

import com.platform.entity.CommentEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-28 17:03:40
 */
public interface CommentService extends BaseService<CommentEntity> {

    /**
     * 修改状态
     *
     * @param comment
     * @return
     */
    int toggleStatus(CommentEntity comment);
}
