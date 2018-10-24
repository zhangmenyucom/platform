package com.platform.entity;/**
 * ${author} on 2018/10/24.
 */

import lombok.Data;

/**
 * @author zhangxiaolu
 * @描述 菜单
 * @since 2018/10/24 17:44
 */
@Data
public class MenuVo {
    private Integer id;
    private String name;
    private String desc;
    private String url;
    private String iconUrl;
    private boolean isShow;

}
