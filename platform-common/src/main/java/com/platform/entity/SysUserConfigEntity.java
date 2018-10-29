package com.platform.entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 sys_user_config
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-22 17:58:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserConfigEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 小程序id **/
    private String appId;
    /** 密钥 **/
    private String secret;
    /** 支付商户号 **/
    private String mchId;
    /** 支付密钥 **/
    private String paySignKey;
    /** 证书地址 **/
    private String certAddress;
    /**店铺名称**/
    private String storeName;
    /**店铺地址**/
    private String storeAddress;
    /**联系电话**/
    private String phone;
}
