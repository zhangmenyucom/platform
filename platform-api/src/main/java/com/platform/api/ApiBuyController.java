package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.cache.J2CacheUtils;
import com.platform.entity.BuyGoodsVo;
import com.platform.entity.UserVo;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@Api(tags = "商品购买")
@RestController
@RequestMapping("/api/{merchantId}/buy")
public class ApiBuyController extends ApiBaseAction {
    @ApiOperation(value = "商品添加")
    @PostMapping("/add")
    public Object addBuy(@PathVariable("merchantId") Long merchantId, @LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        Long goodsId = jsonParam.getLong("goodsId");
        Long productId = jsonParam.getLong("productId");
        Integer number = jsonParam.getInteger("number");
        BuyGoodsVo goodsVo = new BuyGoodsVo();
        goodsVo.setGoodsId(goodsId);
        goodsVo.setMerchantId(merchantId);
        goodsVo.setProductId(productId);
        goodsVo.setNumber(number);
        J2CacheUtils.put(J2CacheUtils.SHOP_CACHE_NAME, "goods" + loginUser.getUserId() + "", goodsVo);
        return toResponsMsgSuccess("添加成功");
    }
}
