package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 热闹关键词表
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KeywordsVo extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    //关键字
    private String keyword;
    //热销
    private Integer is_hot;
    //默认
    private Integer is_default;
    //显示
    private Integer is_show;
    //排序
    private Integer sort_order;
    //关键词的跳转链接
    private String scheme_url;
    //类型
    private Integer type;
}
