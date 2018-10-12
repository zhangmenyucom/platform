package com.platform.service.impl;

import com.platform.annotation.MerchantFilter;
import com.platform.dao.GoodsDao;
import com.platform.dao.GoodsSpecificationDao;
import com.platform.dao.ProductDao;
import com.platform.entity.GoodsEntity;
import com.platform.entity.GoodsSpecificationEntity;
import com.platform.entity.ProductEntity;
import com.platform.service.ProductService;
import com.platform.utils.BeanUtils;
import com.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-30 14:31:21
 */
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<ProductEntity,ProductDao> implements ProductService {

    @Autowired
    private GoodsSpecificationDao goodsSpecificationDao;
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<ProductEntity> queryList(Map<String, Object> map) {
        List<ProductEntity> list = getDao().queryList(map);

        List<ProductEntity> result = new ArrayList<>();
        //翻译产品规格
        if (null != list && list.size() > 0) {
            for (ProductEntity item : list) {
                String specificationIds = item.getGoodsSpecificationIds();
                String specificationValue = "";
                if (!StringUtils.isNullOrEmpty(specificationIds)) {
                    String[] arr = specificationIds.split("_");

                    for (String goodsSpecificationId : arr) {
                        GoodsSpecificationEntity entity = goodsSpecificationDao.queryObject(Long.valueOf(goodsSpecificationId));
                        if (null != entity) {
                            specificationValue += entity.getValue() + "；";
                        }
                    }
                }
                item.setSpecificationValue(item.getGoodsName() + " " + specificationValue);
                result.add(item);
            }
        }
        return result;
    }

    @Override
    @Transactional
    @MerchantFilter
    public int save(ProductEntity product) {
        int result = 0;
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(product, entity);
        if (!StringUtils.isNullOrEmpty(product.getGoodsSpecificationIds())) {
            String[] goodsSpecificationIdArr = product.getGoodsSpecificationIds().split("_");
            for (int i = 0; i < goodsSpecificationIdArr.length - 1; i++) {
                String[] oneId = goodsSpecificationIdArr[i].split(",");
                String[] twoId = goodsSpecificationIdArr[i + 1].split(",");
                for (int j = 0; j < oneId.length; j++) {
                    for (int k = 0; k < twoId.length; k++) {
                        String strGoodsSpecificationIds;
                        if (StringUtils.isNullOrEmpty(oneId[j]) || StringUtils.isNullOrEmpty(twoId[k])) {
                            continue;
                        }
                        strGoodsSpecificationIds = oneId[j] + "_" + twoId[k];
                        entity.setGoodsSpecificationIds(strGoodsSpecificationIds);
                        result += getDao().save(entity);
                    }
                }
            }
        } else {
            getDao().save(entity);
        }
        return result;
    }

    @Override
    public int update(ProductEntity product) {
        if (StringUtils.isNullOrEmpty(product.getGoodsSpecificationIds())) {
            product.setGoodsSpecificationIds("");
        }
        /**及联保存商品**/
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setId(product.getGoodsId());
        goodsEntity.setRetailPrice(product.getRetailPrice());
        goodsEntity.setMarketPrice(product.getMarketPrice());
        goodsEntity.setGoodsNumber(product.getGoodsNumber());
        goodsEntity.setGoodsSn(product.getGoodsSn());
        goodsEntity.setName(product.getGoodsName());
        goodsDao.update(goodsEntity);
        return getDao().update(product);
    }
}
