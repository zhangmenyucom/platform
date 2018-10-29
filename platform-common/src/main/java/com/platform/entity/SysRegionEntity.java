package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-11-04 11:19:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRegionEntity extends Tree<SysRegionEntity> {

    //主键
    private Long id;

    private Long merchantId;
    //父节点
    private Integer parentId;
    //区域名称
    private String name;
    //类型 0国家 1省份 2地市 3区县
    private Integer type;
    //区域代理Id
    private Integer agencyId;

    //父级名称
    private String parentName;


}
