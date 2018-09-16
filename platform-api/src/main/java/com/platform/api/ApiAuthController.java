package com.platform.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.FullUserInfo;
import com.platform.entity.UserInfo;
import com.platform.entity.UserLevelEnum;
import com.platform.entity.UserVo;
import com.platform.service.ApiUserService;
import com.platform.service.TokenService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiUserUtils;
import com.platform.util.CommonUtil;
import com.platform.utils.CharUtil;
import com.platform.utils.JsonUtil;
import com.platform.utils.R;
import com.platform.validator.Assert;
import com.qiniu.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.util.JSONUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * API登录授权
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-03-23 15:31
 */
@Api(tags = "API登录授权接口")
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController extends ApiBaseAction {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private ApiUserService userService;
    /**
     * 登录
     */
    @IgnoreAuth
    @ApiOperation(value = "登录")
    @PostMapping("login_by_weixin")
    public Object loginByWeixin() {
        JSONObject jsonParam = this.getJsonRequest();
        FullUserInfo fullUserInfo = null;
        String code = "";
        if (!StringUtils.isNullOrEmpty(jsonParam.getString("code"))) {
            code = jsonParam.getString("code");
        }
        if (null != jsonParam.get("userInfo")) {
            fullUserInfo = jsonParam.getObject("userInfo", FullUserInfo.class);
        }

        Map<String, Object> resultObj = new HashMap<>(0);
        UserInfo userInfo = fullUserInfo.getUserInfo();

        /**获取openid**/
        /**通过自定义工具类组合出小程序需要的登录凭证 code**/
        String requestUrl = ApiUserUtils.getWebAccess(code);
        logger.info("》》》组合token为：" + requestUrl);
        String res = restTemplate.getForObject(requestUrl, String.class);
        System.out.println("res:"+res);
        JSONObject sessionData = JSON.parseObject(res);
        System.out.println("sessionData"+ JsonUtil.getJsonByObj(sessionData));

        if (null == sessionData || StringUtils.isNullOrEmpty(sessionData.getString("openid"))) {
            return toResponsFail("登录失败");
        }
        /**验证用户信息完整性**/
        System.out.println("fullUserInfo:"+JsonUtil.getJsonByObj(fullUserInfo));
        /*String sha1 = CommonUtil.getSha1(fullUserInfo.getRawData() + sessionData.getString("session_key"));
        System.out.println("shal"+sha1);
        if (!fullUserInfo.getSignature().equals(sha1)) {
            return toResponsFail("登录失败");
        }*/
        Date nowTime = new Date();
        UserVo userVo = userService.queryByOpenId(sessionData.getString("openid"));
        if (null == userVo) {
            userVo = new UserVo();
            userVo.setUsername(userInfo.getNickName());
            userVo.setPassword(sessionData.getString("openid"));
            userVo.setRegister_time(nowTime);
            userVo.setRegister_ip(this.getClientIp());
            userVo.setLast_login_ip(userVo.getRegister_ip());
            userVo.setLast_login_time(userVo.getRegister_time());
            userVo.setWeixin_openid(sessionData.getString("openid"));
            userVo.setAvatar(userInfo.getAvatarUrl());
            /**性别 0：未知、1：男、2：女**/
            userVo.setGender(userInfo.getGender());
            userVo.setNickname(userInfo.getNickName());
            userVo.setMobile(userInfo.getMobile());
            userVo.setUser_level_id(UserLevelEnum.NORMAL.getLevelId());
            userVo.setPoint(0L);
            userService.save(userVo);
        } else {
            userVo.setLast_login_ip(this.getClientIp());
            userVo.setLast_login_time(nowTime);
            userService.update(userVo);
        }

        Map<String, Object> tokenMap = tokenService.createToken(userVo.getUserId());
        String token = MapUtils.getString(tokenMap, "token");
        System.out.println("token"+token);

        if (null == userInfo || StringUtils.isNullOrEmpty(token)) {
            return toResponsFail("登录失败");
        }
        userInfo.setMobile(userVo.getMobile());
        userInfo.setPoint(userVo.getPoint());
        resultObj.put("token", token);
        resultObj.put("userInfo", userInfo);
        resultObj.put("userId", userVo.getUserId());
        return toResponsSuccess(resultObj);
    }
    @Autowired
    private TokenService tokenService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("login")
    @ApiOperation(value = "登录接口")
    public R login(String mobile, String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        long userId = userService.login(mobile, password);

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);

        return R.ok(map);
    }
}
