package com.platform.dao;


import com.platform.vo.SignRecordVo;
import org.apache.ibatis.annotations.Param;

/**
 * Dao
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-09 21:48:06
 */
public interface ApiSignRecordMapper extends BaseDao<SignRecordVo> {

    SignRecordVo queryLatestSign(@Param("userId") Long userId);

}
