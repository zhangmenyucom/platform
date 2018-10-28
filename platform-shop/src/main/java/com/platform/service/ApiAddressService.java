package com.platform.service;

import com.platform.dao.ApiAddressMapper;
import com.platform.service.impl.BaseServiceImpl;
import com.platform.vo.AddressVo;
import org.springframework.stereotype.Service;


@Service
public class ApiAddressService extends BaseServiceImpl<AddressVo,ApiAddressMapper> {

    public int resetDefaultAdress(AddressVo entity) {
        return this.getDao().resetDefaultAddress(entity);
    }
}
