package com.platform.controller;

import com.platform.entity.GiftEntity;
import com.platform.entity.GoodsEntity;
import com.platform.service.GiftService;
import com.platform.service.GoodsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-12 18:50:18
 */
@RestController
@RequestMapping("gift")
public class GiftController {
    @Autowired
    private GiftService giftService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("gift:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<GiftEntity> giftList = giftService.queryList(query);
        int total = giftService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(giftList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("gift:info")
    public R info(@PathVariable("id") Long id) {
        GiftEntity gift = giftService.queryObject(id);
        return R.ok().put("gift", gift);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("gift:save")
    public R save(@RequestBody GiftEntity gift) {
        if(gift.getGoodsId()!=null){
            GoodsEntity goodsEntity = goodsService.queryObject(gift.getGoodsId());
            gift.setPicUrl(goodsEntity.getPrimaryPicUrl());
            gift.setName(goodsEntity.getName());
        }
        giftService.save(gift);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("gift:update")
    public R update(@RequestBody GiftEntity gift) {
        giftService.update(gift);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("gift:delete")
    public R delete(@RequestBody Long[] ids) {
        giftService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<GiftEntity> list = giftService.queryList(params);
        return R.ok().put("list", list);
    }
}
