package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:39
 */
@Data
public class CategoryVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //分类名称
    private String name;
    //关键字
    private String keywords;
    //描述
    private String front_desc;
    //父节点
    private Long parent_id;
    //排序
    private Integer sort_order;
    //首页展示
    private Integer show_index;
    //显示
    private Integer is_show;
    //banner图片
    private String banner_url;
    //icon链接
    private String icon_url;
    //图片
    private String img_url;
    //手机banner
    private String wap_banner_url;
    //级别
    private String level;
    //类型
    private Integer type;
    //
    private String front_name;

    private Boolean checked;

    private List<CategoryVo> subCategoryList;
}
