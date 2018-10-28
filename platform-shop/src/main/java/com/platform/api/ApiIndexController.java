package com.platform.api;

import com.github.pagehelper.PageHelper;
import com.platform.annotation.IgnoreAuth;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.platform.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "首页接口文档")
@RestController
@RequestMapping("/api/{merchantId}/index")
public class ApiIndexController extends ApiBaseAction {
    @Autowired
    private ApiAdService adService;
    @Autowired
    private ApiChannelService channelService;
    @Autowired
    private ApiGoodsService goodsService;
    @Autowired
    private ApiBrandService brandService;
    @Autowired
    private ApiTopicService topicService;
    @Autowired
    private ApiCategoryService categoryService;
    @Autowired
    private ApiCartService cartService;
    

    /**
     * app首页
     */
    @ApiOperation(value = "首页")
    @IgnoreAuth
    @GetMapping(value = "index")
    public Object index(@PathVariable("merchantId") Long merchantId) {
        Map<String, Object> resultObj = new HashMap<>(16);
        //
        Map<String, Object> param = new HashMap<>(16);
        param.put("ad_position_id", 1);
        List<AdVo> banner = adService.queryList(param,merchantId);
        resultObj.put("banner", banner);
        //
        param = new HashMap<>(16);
        param.put("sidx", "sort_order ");
        param.put("order", "asc ");
        List<ChannelVo> channel = channelService.queryList(param,merchantId);
        resultObj.put("channel", channel);
        //
        param = new HashMap<>(16);
        param.put("is_new", 1);
        param.put("is_delete", 0);
        param.put("fields", "id, name, list_pic_url, retail_price");
        PageHelper.startPage(0, 4, false);
        List<GoodsVo> newGoods = goodsService.queryList(param,merchantId);
        resultObj.put("newGoodsList", newGoods);
        //
        param = new HashMap<>(16);
        param.put("is_hot", "1");
        param.put("is_delete", 0);
        param.put("merchantId", merchantId);
        PageHelper.startPage(0, 3, false);
        List<GoodsVo> hotGoods = goodsService.queryHotGoodsList(param);
        resultObj.put("hotGoodsList", hotGoods);
        // 当前购物车中
        List<CartVo> cartList = new ArrayList<CartVo>();
        if (null != getUserId()) {
            //查询列表数据
            Map<String, Object> cartParam = new HashMap<>(16);
            cartParam.put("user_id", getUserId());
            cartList = cartService.queryList(cartParam,merchantId);
        }
        if (null != cartList && cartList.size() > 0 && null != hotGoods && hotGoods.size() > 0) {
            for (GoodsVo goodsVo : hotGoods) {
                for (CartVo cartVo : cartList) {
                    if (goodsVo.getId().equals(cartVo.getGoods_id())) {
                        goodsVo.setCart_num(cartVo.getNumber());
                    }
                }
            }
        }
        //
        param = new HashMap<>(16);
        param.put("is_new", 1);
        param.put("sidx", "new_sort_order ");
        param.put("order", "asc ");
        param.put("offset", 0);
        param.put("limit", 4);
        List<BrandVo> brandList = brandService.queryList(param,merchantId);
        resultObj.put("brandList", brandList);

        param = new HashMap<>(16);
        param.put("offset", 0);
        param.put("limit", 3);
        List<TopicVo> topicList = topicService.queryList(param,merchantId);
        resultObj.put("topicList", topicList);

        param = new HashMap<>(16);
        param.put("parent_id", 0);
        param.put("notName", "推荐");//<>
        List<CategoryVo> categoryList = categoryService.queryList(param,merchantId);
        List<Map<String, Object>> newCategoryList = new ArrayList<>();

        for (CategoryVo categoryItem : categoryList) {
            param.remove("fields");
            param.put("parent_id", categoryItem.getId());
            List<CategoryVo> categoryEntityList = categoryService.queryList(param,merchantId);
            List<Long> childCategoryIds = new ArrayList<>();
            for (CategoryVo categoryEntity : categoryEntityList) {
                childCategoryIds.add(categoryEntity.getId());
            }
            //
            param = new HashMap<>(16);
            param.put("categoryIds", childCategoryIds);
            param.put("fields", "id as id, name as name, list_pic_url as list_pic_url, retail_price as retail_price");
            PageHelper.startPage(0, 7, false);
            List<GoodsVo> categoryGoods = goodsService.queryList(param,merchantId);
            Map<String, Object> newCategory = new HashMap<>(16);
            newCategory.put("id", categoryItem.getId());
            newCategory.put("name", categoryItem.getName());
            newCategory.put("goodsList", categoryGoods);
            newCategoryList.add(newCategory);
        }
        resultObj.put("categoryList", newCategoryList);
        return toResponsSuccess(resultObj);
    }


    /**
     * app首页
     */
    @ApiOperation(value = "新商品信息")
    @IgnoreAuth
    @GetMapping(value = "newGoods")
    public Object newGoods(@PathVariable("merchantId") Long merchantId) {
        Map<String, Object> resultObj = new HashMap<>(16);
        //
        Map<String, Object> param = new HashMap<>(16);
        param.put("is_new", 1);
        param.put("is_delete", 0);
        param.put("fields", "id, name, list_pic_url, retail_price");
        PageHelper.startPage(0, 4, false);
        List<GoodsVo> newGoods = goodsService.queryList(param,merchantId);
        resultObj.put("newGoodsList", newGoods);
        //

        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "新热门商品信息")
    @IgnoreAuth
    @GetMapping(value = "hotGoods")
    public Object hotGoods(@PathVariable("merchantId") Long merchantId) {
        Map<String, Object> resultObj = new HashMap<>(16);
        //
        Map<String, Object> param = new HashMap<>(16);
        param.put("is_hot", "1");
        param.put("is_delete", 0);
        param.put("merchantId", merchantId);
        PageHelper.startPage(0, 3, false);
        List<GoodsVo> hotGoods = goodsService.queryHotGoodsList(param);
        resultObj.put("hotGoodsList", hotGoods);
        //

        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "topic")
    @IgnoreAuth
    @GetMapping(value = "topic")
    public Object topic(@PathVariable("merchantId") Long merchantId) {
        Map<String, Object> resultObj = new HashMap<>(16);
        //
        Map<String, Object> param = new HashMap<>(16);
        param.put("offset", 0);
        param.put("limit", 3);
        List<TopicVo> topicList = topicService.queryList(param,merchantId);
        resultObj.put("topicList", topicList);
        //

        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "brand")
    @IgnoreAuth
    @GetMapping(value = "brand")
    public Object brand(@PathVariable("merchantId") Long merchantId) {
        Map<String, Object> resultObj = new HashMap<>(16);
        //
        Map<String, Object> param = new HashMap<>(16);
        param.put("is_new", 1);
        param.put("sidx", "new_sort_order ");
        param.put("order", "asc ");
        param.put("offset", 0);
        param.put("limit", 4);
        List<BrandVo> brandList = brandService.queryList(param,merchantId);
        resultObj.put("brandList", brandList);
        //

        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "category")
    @IgnoreAuth
    @GetMapping(value = "category")
    public Object category(@PathVariable("merchantId") Long merchantId) {
        Map<String, Object> resultObj = new HashMap<>(16);
        //
        Map<String, Object> param = new HashMap<>(16);
        param = new HashMap<>(16);
        param.put("parent_id", 0);
        param.put("notName", "推荐");//<>
        List<CategoryVo> categoryList = categoryService.queryList(param,merchantId);
        List<Map<String, Object>> newCategoryList = new ArrayList<>();

        for (CategoryVo categoryItem : categoryList) {
            param.remove("fields");
            param.put("parent_id", categoryItem.getId());
            List<CategoryVo> categoryEntityList = categoryService.queryList(param,merchantId);
            List<Long> childCategoryIds = new ArrayList<>();
            for (CategoryVo categoryEntity : categoryEntityList) {
                childCategoryIds.add(categoryEntity.getId());
            }
            //
            param = new HashMap<>(16);
            param.put("categoryIds", childCategoryIds);
            param.put("fields", "id as id, name as name, list_pic_url as list_pic_url, retail_price as retail_price");
            PageHelper.startPage(0, 7, false);
            List<GoodsVo> categoryGoods = goodsService.queryList(param,merchantId);
            Map<String, Object> newCategory = new HashMap<>(16);
            newCategory.put("id", categoryItem.getId());
            newCategory.put("name", categoryItem.getName());
            newCategory.put("goodsList", categoryGoods);
            newCategoryList.add(newCategory);
        }
        resultObj.put("categoryList", newCategoryList);
        //

        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "banner")
    @IgnoreAuth
    @GetMapping(value = "banner")
    public Object banner(@PathVariable("merchantId") Long merchantId) {
        Map<String, Object> resultObj = new HashMap<>(16);
        //
        Map<String, Object> param = new HashMap<>(16);
        param.put("ad_position_id", 1);
        List<AdVo> banner = adService.queryList(param,merchantId);
        resultObj.put("banner", banner);
        //

        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "channel")
    @IgnoreAuth
    @GetMapping(value = "channel")
    public Object channel(@PathVariable("merchantId") Long merchantId) {
        Map<String, Object> resultObj = new HashMap<>(16);
        //
        Map<String, Object> param = new HashMap<>(16);
        param = new HashMap<>(16);
        param.put("sidx", "sort_order ");
        param.put("order", "asc ");
        List<ChannelVo> channel = channelService.queryList(param,merchantId);
        resultObj.put("channel", channel);
        //

        return toResponsSuccess(resultObj);
    }
}