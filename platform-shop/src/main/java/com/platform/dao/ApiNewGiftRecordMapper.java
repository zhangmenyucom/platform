package com.platform.dao;

import com.platform.vo.NewGiftRecordVo;
import org.apache.ibatis.annotations.Param;

/**
 * Dao
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-10-11 19:05:18
 */
public interface ApiNewGiftRecordMapper extends BaseDao<NewGiftRecordVo> {

    NewGiftRecordVo queryByUserId(@Param("userId") Long userId);
}
