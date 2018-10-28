package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;


/**
 * 规格表
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class SpecificationVo extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**规范名称**/
    private String name;
    /**排序**/
    private Integer sort_order;
}
