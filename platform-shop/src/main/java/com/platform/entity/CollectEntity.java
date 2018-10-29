package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-13 10:41:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CollectEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //用户Id
    private Long userId;
    private String userName;
    //产品Id
    private Integer valueId;
    private String valueName;
    //添加时间
    private Date addTime;
    //是否提醒
    private Integer isAttention;
    //
    private Integer typeId;
}
