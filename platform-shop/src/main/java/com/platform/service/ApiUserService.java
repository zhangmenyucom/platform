package com.platform.service;

import com.platform.dao.ApiUserLevelMapper;
import com.platform.dao.ApiUserMapper;
import com.platform.service.impl.BaseServiceImpl;
import com.platform.utils.RRException;
import com.platform.validator.Assert;
import com.platform.vo.SmsLogVo;
import com.platform.vo.UserDetailVo;
import com.platform.vo.UserLevelVo;
import com.platform.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ApiUserService extends BaseServiceImpl<UserVo, ApiUserMapper> {
    @Autowired
    private ApiUserLevelMapper userLevelDao;


    public UserDetailVo queryUserDetailInfo(Long userId) {

        UserDetailVo userDetailVo = getDao().queryUserDetailInfo(userId);
        if (userDetailVo != null) {
            Map<String, Object> map = new HashMap<>(16);
            map.put("parentId", userDetailVo.getUserId());
            List<UserVo> userVos = getDao().queryList(map);
            userDetailVo.setSubUserList(userVos);
            userVos.forEach(uv -> {
                map.put("parentId", uv.getUserId());
                List<UserVo> subVos = getDao().queryList(map);
                uv.setSubUserList(subVos);
            });
        }
        return userDetailVo;
    }

    public UserVo queryByOpenId(String openId) {
        return getDao().queryByOpenId(openId);
    }


    public void save(String mobile, String password) {
        UserVo user = new UserVo();
        user.setMobile(mobile);
        user.setUsername(mobile);
        user.setPassword(DigestUtils.sha256Hex(password));
        user.setRegister_time(new Date());
        getDao().save(user);
    }


    public UserVo queryByMobile(String mobile) {
        return getDao().queryByMobile(mobile);
    }

    public long login(String mobile, String password) {
        UserVo user = queryByMobile(mobile);
        Assert.isNull(user, "手机号或密码错误");

        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            throw new RRException("手机号或密码错误");
        }

        return user.getUserId();
    }

    public SmsLogVo querySmsCodeByUserId(Long user_id) {
        return getDao().querySmsCodeByUserId(user_id);
    }


    public int saveSmsCodeLog(SmsLogVo smsLogVo) {
        return getDao().saveSmsCodeLog(smsLogVo);
    }

    public String getUserLevel(UserVo loginUser) {
        String result = "普通用户";
        UserLevelVo userLevelVo = userLevelDao.queryObject(loginUser.getUser_level_id());
        if (null != userLevelVo) {
            result = userLevelVo.getName();
        }
        return result;
    }
}