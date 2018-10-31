package com.platform.dao;

import com.platform.vo.SearchHistoryVo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-11 09:16:46
 */
public interface ApiSearchHistoryMapper extends BaseDao<SearchHistoryVo> {
    int deleteByUserId(@Param("user_id") Long userId);
}