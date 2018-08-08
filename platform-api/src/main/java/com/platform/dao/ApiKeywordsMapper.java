package com.platform.dao;

import com.platform.entity.KeywordsVo;

import java.util.List;
import java.util.Map;

/**
 * 热闹关键词表
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-11 09:16:46
 */
public interface ApiKeywordsMapper extends BaseDao<KeywordsVo> {
    List<Map> hotKeywordList(Map param);
}
