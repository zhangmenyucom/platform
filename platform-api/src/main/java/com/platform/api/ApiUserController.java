package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.entity.SmsConfig;
import com.platform.entity.SmsLogVo;
import com.platform.entity.UserDetailVo;
import com.platform.entity.UserVo;
import com.platform.service.ApiUserService;
import com.platform.service.SysConfigService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "会员验证")
@RestController
@RequestMapping("/api/user")
public class ApiUserController extends ApiBaseAction {
    @Autowired
    private ApiUserService userService;

    /**
     * 发送短信
     */
    @ApiOperation(value = "发送短信")
    @PostMapping("smscode")
    public Object smscode(@LoginUser UserVo loginUser) {
        JSONObject jsonParams = getJsonRequest();
        String phone = jsonParams.getString("phone");
        /** 一分钟之内不能重复发送短信**/
        SmsLogVo smsLogVo = userService.querySmsCodeByUserId(loginUser.getUserId());
        if (null != smsLogVo && (System.currentTimeMillis() - smsLogVo.getLog_date().getTime()) < 1 * 60000) {
            return toResponsFail("短信已发送");
        }
        /**生成验证码**/
        String sms_code = CharUtil.getRandomNum(4);
        String msgContent = "您的验证码是：" + sms_code + "，请在页面中提交验证码完成验证。";
        /**发送短信**/
        try {
            String[] params = {sms_code, "2"};
            SmsSingleSenderResult senderResult = MSUtil.sendMessage(phone, params);
            if (senderResult.result == 0){
                smsLogVo = new SmsLogVo();
                smsLogVo.setLog_date(new Date());
                smsLogVo.setUser_id(loginUser.getUserId());
                smsLogVo.setPhone(phone);
                smsLogVo.setSms_code(sms_code);
                smsLogVo.setSms_text(msgContent);
                userService.saveSmsCodeLog(smsLogVo);
                return toResponsSuccess("短信发送成功");
            } else{
                return toResponsFail("短信发送失败");
            }

        } catch (Exception e) {
            return toResponsFail("短信发送失败");
        }
    }

    /**
     * 获取当前会员等级
     *
     * @param loginUser
     * @return
     */
    @ApiOperation(value = "获取当前会员等级")
    @GetMapping("getUserLevel")
    public Object getUserLevel(@LoginUser UserVo loginUser) {
        String userLevel = userService.getUserLevel(loginUser);
        return toResponsSuccess(userLevel);
    }

    /**
     * 绑定手机
     */
    @ApiOperation(value = "绑定手机")
    @PostMapping("bindMobile")
    public Object bindMobile(@LoginUser UserVo loginUser) {
        JSONObject jsonParams = getJsonRequest();
        SmsLogVo smsLogVo = userService.querySmsCodeByUserId(loginUser.getUserId());

        String mobile_code = jsonParams.getString("mobile_code");
        String mobile = jsonParams.getString("mobile");

        if (!mobile_code.equals(smsLogVo.getSms_code())) {
            return toResponsFail("验证码错误");
        }
        UserVo userVo = userService.queryObject(loginUser.getUserId());
        userVo.setMobile(mobile);
        userService.update(userVo);
        return toResponsSuccess("手机绑定成功");
    }

    /**
     * 绑定手机(微信获取)
     */
    @ApiOperation(value = "通过微信绑定手机")
    @GetMapping("bindMobileWx")
    public Object bindMobileWithWX(@LoginUser UserVo loginUser, @RequestParam("mobile") String mobile) {
        UserVo userVo = userService.queryObject(loginUser.getUserId());
        userVo.setMobile(mobile);
        userService.update(userVo);
        return toResponsSuccess("手机绑定成功");
    }

    /**
     * 签到
     */
    @ApiOperation(value = "签到")
    @PostMapping("sign")
    public Object sign(@LoginUser UserVo loginUser) {
        UserVo userVo = userService.queryObject(loginUser.getUserId());
        userVo.setPoint(userVo.getPoint() + 10);
        userService.update(userVo);
        return toResponsSuccess("签到成功");
    }

    /**
     * 获取用户详细信息
     */
    @ApiOperation(value = "获取用户详细信息")
    @GetMapping("detailInfo")
    public Object getUserDetailInfo(UserVo loginUser) {
        UserDetailVo userDetailVo = userService.queryUserDetailInfo(loginUser.getUserId());
        return toResponsSuccess(userDetailVo);
    }


}