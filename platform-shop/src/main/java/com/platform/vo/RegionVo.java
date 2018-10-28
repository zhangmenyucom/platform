package com.platform.vo;

import com.platform.entity.BaseEntity;
import com.platform.entity.SysRegionEntity;
import lombok.Data;

/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class RegionVo extends BaseEntity {

    /**父节点**/
    private Integer parent_id;
    /**区域名称**/
    private String name;
    /**类型 0国家 1省份 2地市 3区县**/
    private Integer type;
    /**区域代理Id**/
    private Integer agency_id;

    public RegionVo() {
    }

    public RegionVo(SysRegionEntity regionEntity) {
        this.setId(regionEntity.getId());
        parent_id = regionEntity.getParentId();
        name = regionEntity.getName();
        type = regionEntity.getType();
        agency_id = regionEntity.getAgencyId();
    }
}
