package com.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
@ApiModel
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty("主键id")
    private Long userId;
    //会员名称
    @ApiModelProperty("会员名称")
    private String username;
    //会员密码
    @ApiModelProperty("会员密码")
    private String password;
    //性别
    @ApiModelProperty("性别")
    private Integer gender;
    //出生日期
    @ApiModelProperty("出生日期")
    private Date birthday;
    //注册时间
    @ApiModelProperty("注册时间")
    private Date register_time;
    //最后登录时间
    @ApiModelProperty("最后登录时间")
    private Date last_login_time;
    //最后登录Ip
    @ApiModelProperty("最后登录Ip")
    private String last_login_ip;
    //会员等级
    @ApiModelProperty("会员等级")
    private Integer user_level_id;
    //别名
    @ApiModelProperty("别名")
    private String nickname;
    //手机号码
    @ApiModelProperty("手机号码")
    private String mobile;
    //注册Ip
    @ApiModelProperty("注册Ip")
    private String register_ip;
    //头像
    @ApiModelProperty("头像")
    private String avatar;
    //微信Id
    @ApiModelProperty("微信Id")
    private String weixin_openid;
}
