package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 sign_record
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-09 21:48:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SignRecordVo extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 用户id **/
    private Long userId;
    /** 获得积分 **/
    private Long gainPoint;
    /**  **/
    private Date signDate;
}