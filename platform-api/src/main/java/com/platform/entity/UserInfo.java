package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 头像url
     **/
    private String avatarUrl;
    /**
     * 城市
     **/
    private String city;
    /**
     * 性别
     **/
    private Integer gender;
    /**
     * 昵称
     **/
    private String nickName;
    /**
     * 省
     **/
    private String province;

    /**
     * 手机号
     **/
    private String mobile;

    /**
     * 积分
     **/
    private Long point;

}
