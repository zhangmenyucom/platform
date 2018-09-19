package com.platform.service.impl;

import com.platform.dao.CartDao;
import com.platform.entity.CartEntity;
import com.platform.service.CartService;
import org.springframework.stereotype.Service;


@Service("cartService")
public class CartServiceImpl extends BaseServiceImpl<CartEntity,CartDao> implements CartService {
	
}
