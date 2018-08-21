package com.platform.entity;

import lombok.Data;

/**
 * 名称：ResultMap <br>
 * 描述：查询表信息返回的BaseResultMap<br>
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-09-17 20:20
 */
@Data
public class ResultMap {
    /**
     * 数据库字段名
     */
    private String columnName;
    /**
     * 字段类型
     */
    private String dataType;
    /**
     * 字段注释
     */
    private String columnComment;
    /**
     * 主键
     */
    private String columnKey;
}
