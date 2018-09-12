package com.platform.controller;

import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.GiftExchangeRecordEntity;
import com.platform.service.GiftExchangeRecordService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-12 18:50:18
 */
@RestController
@RequestMapping("giftexchangerecord")
public class GiftExchangeRecordController {
    @Autowired
    private GiftExchangeRecordService giftExchangeRecordService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("giftexchangerecord:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<GiftExchangeRecordEntity> giftExchangeRecordList = giftExchangeRecordService.queryList(query);
        int total = giftExchangeRecordService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(giftExchangeRecordList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("giftexchangerecord:info")
    public R info(@PathVariable("id") Long id) {
        GiftExchangeRecordEntity giftExchangeRecord = giftExchangeRecordService.queryObject(id);
        return R.ok().put("giftExchangeRecord", giftExchangeRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("giftexchangerecord:save")
    public R save(@RequestBody GiftExchangeRecordEntity giftExchangeRecord) {
        giftExchangeRecordService.save(giftExchangeRecord);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("giftexchangerecord:update")
    public R update(@RequestBody GiftExchangeRecordEntity giftExchangeRecord) {
        giftExchangeRecordService.update(giftExchangeRecord);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("giftexchangerecord:delete")
    public R delete(@RequestBody Long[] ids) {
        giftExchangeRecordService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<GiftExchangeRecordEntity> list = giftExchangeRecordService.queryList(params);
        return R.ok().put("list", list);
    }
}
