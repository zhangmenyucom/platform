package com.platform.service.impl;

import com.platform.annotation.DataFilter;
import com.platform.annotation.MerchantFilter;
import com.platform.dao.GoodsAttributeDao;
import com.platform.dao.GoodsDao;
import com.platform.dao.GoodsGalleryDao;
import com.platform.dao.ProductDao;
import com.platform.entity.*;
import com.platform.service.GoodsService;
import com.platform.utils.RRException;
import com.platform.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-21 21:19:49
 */
@Service("goodsService")
public class GoodsServiceImpl  extends  BaseServiceImpl<GoodsEntity,GoodsDao> implements GoodsService {

    @Autowired
    private GoodsAttributeDao goodsAttributeDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private GoodsGalleryDao goodsGalleryDao;



    @Override
    @DataFilter(userAlias = "goods.create_user_id", deptAlias = "goods.create_user_dept_id")
    @MerchantFilter
    public List<GoodsEntity> queryList(Map<String, Object> map) {
        return getDao().queryList(map);
    }

    @Override
    @DataFilter(userAlias = "goods.create_user_id", deptAlias = "goods.create_user_dept_id")
    public int queryTotal(Map<String, Object> map) {
        return getDao().queryTotal(map);
    }

    @Override
    @Transactional
    @MerchantFilter
    public int save(GoodsEntity goods) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        Map<String, Object> map = new HashMap<>(1);
        map.put("name", goods.getName());
        List<GoodsEntity> list = queryList(map);
        if (null != list && list.size() != 0) {
            throw new RRException("商品名称已存在！");
        }
        Long id = getDao().queryMaxId() + 1L;
        goods.setId(id);

        //保存产品信息
        ProductEntity productEntity = new ProductEntity();
        productEntity.setGoodsId(id);
        productEntity.setGoodsSn(goods.getGoodsSn());
        productEntity.setGoodsNumber(goods.getGoodsNumber());
        productEntity.setRetailPrice(goods.getRetailPrice());
        productEntity.setMarketPrice(goods.getMarketPrice());
        productEntity.setMerchantId(goods.getMerchantId());
        productEntity.setGoodsSpecificationIds("");
        productDao.save(productEntity);

        goods.setAddTime(new Date());
        goods.setPrimaryProductId(productEntity.getId());

        //保存商品详情页面显示的属性
        List<GoodsAttributeEntity> attributeEntityList = goods.getAttributeEntityList();
        if (null != attributeEntityList && attributeEntityList.size() > 0) {
            for (GoodsAttributeEntity item : attributeEntityList) {
                item.setGoodsId(id);
                item.setMerchantId(goods.getMerchantId());
                goodsAttributeDao.save(item);
            }
        }

        //商品轮播图
        List<GoodsGalleryEntity> galleryEntityList = goods.getGoodsImgList();
        if (null != galleryEntityList && galleryEntityList.size() > 0) {
            for (GoodsGalleryEntity galleryEntity : galleryEntityList) {
                galleryEntity.setGoodsId(id);
                galleryEntity.setMerchantId(goods.getMerchantId());
                goodsGalleryDao.save(galleryEntity);
            }
        }

        goods.setIsDelete(0);
        goods.setCreateUserDeptId(user.getDeptId());
        goods.setCreateUserId(user.getUserId());
        goods.setUpdateUserId(user.getUserId());
        goods.setUpdateTime(new Date());
        return getDao().save(goods);
    }

    @Override
    @Transactional
    public int update(GoodsEntity goods) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        List<GoodsAttributeEntity> attributeEntityList = goods.getAttributeEntityList();
        if (null != attributeEntityList && attributeEntityList.size() > 0) {
            for (GoodsAttributeEntity goodsAttributeEntity : attributeEntityList) {
                int result = goodsAttributeDao.updateByGoodsIdAttributeId(goodsAttributeEntity);
                if (result == 0) {
                    goodsAttributeDao.save(goodsAttributeEntity);
                }
            }
        }
        goods.setUpdateUserId(user.getUserId());
        goods.setUpdateTime(new Date());
        //商品轮播图
        //修改时全删全插
        List<GoodsGalleryEntity> galleryEntityList = goods.getGoodsImgList();
        Map<String, Long> map = new HashMap<>(1);
        map.put("goodsId", goods.getId());
        goodsGalleryDao.deleteByGoodsId(map);

        /**保存产品信息**/
        ProductEntity productEntity = new ProductEntity();
        productEntity.setGoodsId(goods.getId());
        productEntity.setGoodsSn(goods.getGoodsSn());
        productEntity.setGoodsNumber(goods.getGoodsNumber());
        productEntity.setRetailPrice(goods.getRetailPrice());
        productEntity.setMarketPrice(goods.getMarketPrice());
        productEntity.setGoodsSpecificationIds("");
        productDao.update(productEntity);

        if (null != galleryEntityList && galleryEntityList.size() > 0) {
            for (GoodsGalleryEntity galleryEntity : galleryEntityList) {
                galleryEntity.setGoodsId(goods.getId());
                goodsGalleryDao.save(galleryEntity);
            }
        }
        return getDao().update(goods);
    }


    @Override
    @Transactional
    public int back(Long[] ids) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        int result = 0;
        for (Long id : ids) {
            GoodsEntity goodsEntity = queryObject(id);
            goodsEntity.setIsDelete(0);
            goodsEntity.setIsOnSale(1);
            goodsEntity.setUpdateUserId(user.getUserId());
            goodsEntity.setUpdateTime(new Date());
            result += getDao().update(goodsEntity);
        }
        return result;
    }

    @Override
    public int enSale(Long id) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        GoodsEntity goodsEntity = queryObject(id);
        if (1 == goodsEntity.getIsOnSale()) {
            throw new RRException("此商品已处于上架状态！");
        }
        goodsEntity.setIsOnSale(1);
        goodsEntity.setUpdateUserId(user.getUserId());
        goodsEntity.setUpdateTime(new Date());
        return getDao().update(goodsEntity);
    }

    @Override
    public int unSale(Long id) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        GoodsEntity goodsEntity = queryObject(id);
        if (0 == goodsEntity.getIsOnSale()) {
            throw new RRException("此商品已处于下架状态！");
        }
        goodsEntity.setIsOnSale(0);
        goodsEntity.setUpdateUserId(user.getUserId());
        goodsEntity.setUpdateTime(new Date());
        return getDao().update(goodsEntity);
    }
}
