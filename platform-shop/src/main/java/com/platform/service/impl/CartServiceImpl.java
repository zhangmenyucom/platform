package com.platform.service.impl;

import com.platform.dao.CartDao;
import com.platform.entity.CartEntity;
import com.platform.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("cartService")
public class CartServiceImpl extends BaseServiceImpl<CartEntity,CartDao> implements CartService {
	
}
