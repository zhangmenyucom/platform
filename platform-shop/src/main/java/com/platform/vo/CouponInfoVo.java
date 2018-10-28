package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-09-28 11:13<br>
 * 描述: CouponInfoVo <br>
 */
@Data
public class CouponInfoVo extends BaseEntity {
    // 显示信息
    private String msg;
    // 是否凑单 0否 1是
    private Integer type = 0;
}