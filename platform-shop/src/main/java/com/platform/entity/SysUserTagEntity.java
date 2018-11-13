package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 sys_user_tag
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-11-13 17:10:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserTagEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**  **/
    private Long id;
    /**
     * 商家id
     **/
    private Long merchantId;
    /**
     * 标签名
     **/
    private String tag;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 更新时间
     **/
    private Date updateTime;
}
