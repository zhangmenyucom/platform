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

import com.platform.entity.NewGiftRecordEntity;
import com.platform.service.NewGiftRecordService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-10-11 19:05:18
 */
@RestController
@RequestMapping("newgiftrecord")
public class NewGiftRecordController {
    @Autowired
    private NewGiftRecordService newGiftRecordService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("newgiftrecord:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<NewGiftRecordEntity> newGiftRecordList = newGiftRecordService.queryList(query);
        int total = newGiftRecordService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(newGiftRecordList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("newgiftrecord:info")
    public R info(@PathVariable("id") Long id) {
        NewGiftRecordEntity newGiftRecord = newGiftRecordService.queryObject(id);
        return R.ok().put("newGiftRecord", newGiftRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("newgiftrecord:save")
    public R save(@RequestBody NewGiftRecordEntity newGiftRecord) {
        newGiftRecordService.save(newGiftRecord);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("newgiftrecord:update")
    public R update(@RequestBody NewGiftRecordEntity newGiftRecord) {
        newGiftRecordService.update(newGiftRecord);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("newgiftrecord:delete")
    public R delete(@RequestBody Long[] ids) {
        newGiftRecordService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<NewGiftRecordEntity> list = newGiftRecordService.queryList(params);
        return R.ok().put("list", list);
    }
}
