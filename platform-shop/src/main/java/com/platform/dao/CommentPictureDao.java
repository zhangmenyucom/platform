package com.platform.dao;

import com.platform.entity.CommentPictureEntity;

/**
 * 评价图片Dao
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-29 14:45:55
 */
public interface CommentPictureDao extends BaseDao<CommentPictureEntity> {
    /**
     * 根据commentId删除
     *
     * @param commentId
     * @return
     */
    int deleteByCommentId(Integer commentId);
}
