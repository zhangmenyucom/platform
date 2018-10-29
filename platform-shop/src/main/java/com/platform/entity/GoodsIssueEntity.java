package com.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 实体
 * 表名 goods_issue
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-23 14:12:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsIssueEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //问题
    private String question;
    //回答
    private String answer;
}
