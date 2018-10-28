package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 会员实体
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-16 15:02:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**父级id**/
    private Integer parentId;
    /**会员名称**/
    private String username;
    /**会员密码**/
    private String password;
    /**性别**/
    private Integer gender;
    /**出生日期**/
    private Date birthday;
    /**注册时间**/
    private Date registerTime;
    /**最后登录时间**/
    private Date lastLoginTime;
    /**最后登录Ip**/
    private String lastLoginIp;
    /**会员等级**/
    private Integer userLevelId;
    /**微信名**/
    private String nickname;
    /**手机号码**/
    private String mobile;
    /**注册Ip**/
    private String registerIp;
    /**头像**/
    private String avatar;
    /**微信Id**/
    private String weixinOpenid;
    /**会员级别**/
    private String levelName;
    /**
     * 积分
     **/
    private Integer point;
    /**
     * 总佣金
     **/
    private BigDecimal totalBalance;
    /**
     * 可用佣金
     **/
    private BigDecimal avilableBalance;
    /**
     * 冻结佣金
     **/
    private BigDecimal lockBalance;
    
}
