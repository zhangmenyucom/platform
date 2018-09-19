package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户Token
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-03-23 15:22:07
 */
@Data
public class TokenEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**用户ID**/
    private Long userId;
    /**token**/
    private String token;
    /**过期时间**/
    private Date expireTime;
}
