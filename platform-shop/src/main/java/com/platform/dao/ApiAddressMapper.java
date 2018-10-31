package com.platform.dao;


import com.platform.vo.AddressVo;

/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-11 09:14:25
 */
public interface ApiAddressMapper extends BaseDao<AddressVo> {

    int resetDefaultAddress(AddressVo addressVo);

}