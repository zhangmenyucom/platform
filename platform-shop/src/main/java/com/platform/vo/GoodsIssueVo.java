package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsIssueVo extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    //商品id
    private String goods_id;
    //问题
    private String question;
    //回答
    private String answer;
}
