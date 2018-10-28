package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChannelVo  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private String name;
    //
    private String url;
    //
    private String icon_url;
    //
    private Integer sort_order;

}
