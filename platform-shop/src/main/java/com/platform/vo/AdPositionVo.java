package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdPositionVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**位置名称**/
    private String name;
    /**宽度**/
    private Integer width;
    /**高度**/
    private Integer height;
    /**描述**/
    private String desc;
}