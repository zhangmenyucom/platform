package com.platform.service.impl;

import com.platform.dao.GoodsGalleryDao;
import com.platform.entity.GoodsGalleryEntity;
import com.platform.service.BaseService;
import com.platform.service.GoodsGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-23 14:41:43
 */
@Service("goodsGalleryService")
public class GoodsGalleryServiceImpl extends BaseServiceImpl<GoodsGalleryEntity,GoodsGalleryDao> implements GoodsGalleryService {
}
