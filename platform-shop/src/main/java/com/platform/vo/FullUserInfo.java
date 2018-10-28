package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class FullUserInfo extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * errMsg
     **/
    private String errMsg;
    /**
     * rawData
     **/
    private String rawData;
    /**
     * userInfo
     **/
    private UserInfo userInfo;
    /**
     * encryptedData
     **/
    private String encryptedData;
    /**
     * iv
     **/
    private String iv;
    /**
     * signature
     **/
    private String signature;
}
