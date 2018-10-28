package com.platform.vo;

import com.platform.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;


/**
 * 商城_用户级别
 * 
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
public class UserLevelVo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**名称**/
	private String name;
	/**描述**/
	private String description;
}
