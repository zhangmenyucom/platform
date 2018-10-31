package com.platform.dao;


import com.platform.vo.SmsLogVo;
import com.platform.vo.UserDetailVo;
import com.platform.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-03-23 15:22:06
 */
public interface ApiUserMapper extends BaseDao<UserVo> {

    UserVo queryByMobile(String mobile);

    UserVo queryByOpenId(@Param("openId") String openId);

    /**
     * 获取用户最后一条短信
     *
     * @param user_id
     * @return
     */
    SmsLogVo querySmsCodeByUserId(@Param("user_id") Long user_id);

    /**
     * 保存短信
     *
     * @param smsLogVo
     * @return
     */
    int saveSmsCodeLog(SmsLogVo smsLogVo);

    /**
     * 查询用户详情
     **/
    UserDetailVo queryUserDetailInfo(Long userId);
}