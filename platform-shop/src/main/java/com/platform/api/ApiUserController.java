package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.platform.annotation.LoginUser;
import com.platform.common.Constants;
import com.platform.entity.EnterpriceToCustomerEntity;
import com.platform.entity.TransferReqBean;
import com.platform.enums.ResponseCodeEnum;
import com.platform.common.vo.EncryptedDataBean;
import com.platform.service.ApiNewGiftRecordService;
import com.platform.service.ApiSignRecordService;
import com.platform.service.ApiUserService;
import com.platform.service.impl.TransferService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.CharUtil;
import com.platform.utils.MSUtil;
import com.platform.utils.R;
import com.platform.utils.WXAppletUserInfo;
import com.platform.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "会员验证")
@RestController
@RequestMapping("/api/{merchantId}/user")
public class ApiUserController extends ApiBaseAction {
    @Autowired
    private ApiUserService userService;

    @Autowired
    private ApiSignRecordService apiSignRecordService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private ApiNewGiftRecordService apiNewGiftRecordService;

    /**
     * 发送短信
     */
    @ApiOperation(value = "发送短信")
    @PostMapping("/smscode")
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
            String[] params = {sms_code, "1"};
            SmsSingleSenderResult senderResult = MSUtil.sendMessage(phone, params);
            if (senderResult.result == 0) {
                smsLogVo = new SmsLogVo();
                smsLogVo.setLog_date(new Date());
                smsLogVo.setUser_id(loginUser.getUserId());
                smsLogVo.setPhone(phone);
                smsLogVo.setSms_code(sms_code);
                smsLogVo.setSms_text(msgContent);
                userService.saveSmsCodeLog(smsLogVo);
                return toResponsSuccess("短信发送成功");
            } else {
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
    @GetMapping("/getUserLevel")
    public Object getUserLevel(@LoginUser UserVo loginUser) {
        String userLevel = userService.getUserLevel(loginUser);
        return toResponsSuccess(userLevel);
    }

    /**
     * 绑定手机
     */
    @ApiOperation(value = "绑定手机")
    @PostMapping("/bindMobile")
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
    @GetMapping("/bindMobileWx")
    public Object bindMobileWithWX(@LoginUser UserVo loginUser, @RequestParam("iv") String iv, @RequestParam("encryptedData") String encryptedData) {
        UserVo userVo = userService.queryObject(loginUser.getUserId());
        try {
            System.out.println("sessionKey:" + Constants.SESSION_KEY_MAP.get(userVo.getUserId()));
            System.out.println("iv:" + iv);
            System.out.println("encryptedData:" + encryptedData);
            EncryptedDataBean decrypt = WXAppletUserInfo.getUserMobile(Constants.SESSION_KEY_MAP.get(userVo.getUserId()), iv, encryptedData);
            userVo.setMobile(decrypt.getPhoneNumber());
            userService.update(userVo);
            return toResponsObject(ResponseCodeEnum.SUCCESS.getCode(), "手机绑定成功", decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("绑定失败");
    }

    /**
     * 签到
     */
    @ApiOperation(value = "签到")
    @GetMapping("/sign")
    public Object sign(@PathVariable("merchantId") Long merchantId, @LoginUser UserVo loginUser) {
        SignRecordVo signRecordVo = apiSignRecordService.queryLatestSign(loginUser.getUserId());
        if (signRecordVo != null) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
            String nowDay = sf.format(new Date());
            String day = sf.format(signRecordVo.getSignDate());
            if (day.equals(nowDay)) {
                return toResponsFail("您今天已经签过到了,请明天再来哦");
            }
        }
        UserVo userVo = userService.queryObject(loginUser.getUserId());
        userVo.setPoint(userVo.getPoint() + 10);
        userService.update(userVo);
        SignRecordVo signRecord = new SignRecordVo();
        signRecord.setGainPoint(10L);
        signRecord.setSignDate(new Date());
        signRecord.setUserId(loginUser.getUserId());
        signRecord.setMerchantId(merchantId);
        apiSignRecordService.save(signRecord);
        return toResponsObject(ResponseCodeEnum.SUCCESS.getCode(), "签到成功,获取10积分", "签到成功");
    }

    /**
     * 获取用户详细信息
     */
    @ApiOperation(value = "获取用户详细信息")
    @GetMapping("/detailInfo")
    public Object getUserDetailInfo(UserVo loginUser) {
        UserDetailVo userDetailVo = userService.queryUserDetailInfo(loginUser.getUserId());
        return toResponsSuccess(userDetailVo);
    }


    /**
     * 新人有礼红包领取
     */
    @ApiOperation(value = "新人有礼红包领取")
    @GetMapping("/new_gift")
    public Object newGift(@LoginUser UserVo loginUser) {
        NewGiftRecordVo recordVo = apiNewGiftRecordService.queryByUserId(loginUser.getUserId());
        if (recordVo != null) {
            return R.error("你已领过新人红包哦");
        }
        NewGiftRecordVo newGiftRecordVo = new NewGiftRecordVo();
        newGiftRecordVo.setUserId(loginUser.getUserId());
        newGiftRecordVo.setHongBao(BigDecimal.valueOf(0.3));
        newGiftRecordVo.setCreateTime(new Date());
        newGiftRecordVo.setMerchantId(loginUser.getMerchantId());
        newGiftRecordVo.setUpdateTime(new Date());
        newGiftRecordVo.setStatus(1);
        newGiftRecordVo.setRemark("新人有礼红包");
        apiNewGiftRecordService.save(newGiftRecordVo);

        TransferReqBean transferReqBean = new TransferReqBean();
        transferReqBean.setWithdrawOrderId(newGiftRecordVo.getId());
        transferReqBean.setAmount(BigDecimal.valueOf(0.3).multiply(BigDecimal.valueOf(100)).intValue());
        transferReqBean.setDesc("新人有礼红包");
        transferReqBean.setOpenId(loginUser.getWeixin_openid());
        transferReqBean.setRealName(loginUser.getNickname());
        transferReqBean.setNeedCheckName(false);
        transferReqBean.setMerchantId(loginUser.getMerchantId());
        EnterpriceToCustomerEntity etoc = transferService.payToCustom(transferReqBean);
        if ("SUCCESS".equalsIgnoreCase(etoc.getResult_code())) {
            return R.ok().put("data", newGiftRecordVo);
        } else {
            newGiftRecordVo.setStatus(0);
            newGiftRecordVo.setRemark(etoc.getErr_code_des());
            apiNewGiftRecordService.update(newGiftRecordVo);
            return R.error(etoc.getErr_code_des());
        }
    }

    /**
     * 新人有礼红包领取
     */
    @ApiOperation(value = "新人有礼红包领取")
    @GetMapping("/has_take_new_gift")
    public R hasTakeNewGift(@LoginUser UserVo loginUser) {
        NewGiftRecordVo recordVo = apiNewGiftRecordService.queryByUserId(loginUser.getUserId());
        if (recordVo != null) {
            return R.ok("你已领过新人红包哦");
        }
        return R.error("未领取");
    }
}