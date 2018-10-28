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
public class RelatedGoodsVo extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品Id
     **/
    private Integer goods_id;
    /**
     * 关联商品id
     **/
    private Integer related_goods_id;

}
