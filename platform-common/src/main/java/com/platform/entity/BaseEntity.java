package com.platform.entity;

import lombok.Data;

import java.util.Date;

/**
 * 对业务实体做公共属性
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017年11月16日 下午10:43:36
 */
@Data
public class BaseEntity {
    /**
     * 主键id
     **/
    private Long id;
    /**
     * 商户id
     **/
    private Long merchantId;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 更新时间
     **/
    private Date updateTime;
}
