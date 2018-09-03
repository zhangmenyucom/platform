package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键**/
    private Long userId;
    /**上级结点**/
    private Long parentId;
    /**会员名称**/
    private String username;
    /**会员密码**/
    private String password;
    /**性别**/
    private Integer gender;
    /**出生日期**/
    private Date birthday;
    /**注册时间**/
    private Date register_time;
    /**最后登录时间**/
    private Date last_login_time;
    /**最后登录Ip**/
    private String last_login_ip;
    /**会员等级**/
    private Integer user_level_id;
    /**会员等级名称**/
    private String userLevel;
    /**别名**/
    private String nickname;
    /**手机号码**/
    private String mobile;
    /**注册Ip**/
    private String register_ip;
    /**头像**/
    private String avatar;
    /**微信Id**/
    private String weixin_openid;
    /**积分**/
    private Long point;
    /**下级列表**/
    private List<UserVo> subUserList;
    /**总佣金**/
    private BigDecimal totalBalance;
    /**可用佣金**/
    private BigDecimal avilableBalance;
    /**冻结佣金**/
    private BigDecimal lockBalance;
}
