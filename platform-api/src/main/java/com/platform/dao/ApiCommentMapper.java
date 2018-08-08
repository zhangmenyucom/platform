package com.platform.dao;

import com.platform.entity.CommentVo;

import java.util.Map;

/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-11 09:14:26
 */
public interface ApiCommentMapper extends BaseDao<CommentVo> {
    int queryhasPicTotal(Map<String, Object> map);
}
