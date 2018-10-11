package com.platform.controller;

import java.util.List;
import java.util.Map;

import com.platform.entity.GoodsEntity;
import com.platform.service.GoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.SeckillGoodsEntity;
import com.platform.service.SeckillGoodsService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-10-11 11:18:21
 */
@RestController
@RequestMapping("seckillgoods")
public class SeckillGoodsController {
    @Autowired
    private SeckillGoodsService seckillGoodsService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seckillgoods:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SeckillGoodsEntity> seckillGoodsList = seckillGoodsService.queryList(query);
        int total = seckillGoodsService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(seckillGoodsList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seckillgoods:info")
    public R info(@PathVariable("id") Long id) {
        SeckillGoodsEntity seckillGoods = seckillGoodsService.queryObject(id);
        return R.ok().put("seckillGoods", seckillGoods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seckillgoods:save")
    public R save(@RequestBody SeckillGoodsEntity seckillGoods) {
        GoodsEntity goodsEntity = goodsService.queryObject(seckillGoods.getGoodsId());
        goodsEntity.setIsOnSale(0);
        goodsService.update(goodsEntity);
        seckillGoodsService.save(seckillGoods);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seckillgoods:update")
    public R update(@RequestBody SeckillGoodsEntity seckillGoods) {
        seckillGoodsService.update(seckillGoods);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seckillgoods:delete")
    public R delete(@RequestBody Long[] ids) {
        seckillGoodsService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<SeckillGoodsEntity> list = seckillGoodsService.queryList(params);
        return R.ok().put("list", list);
    }
}
