package com.platform.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
public class GoodsIssueVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //商品id
    private String goods_id;
    //问题
    private String question;
    //回答
    private String answer;
}
