package com.platform.controller;

import com.platform.entity.SignRecordEntity;
import com.platform.service.SignRecordService;
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
 * @date 2018-09-09 21:48:06
 */
@RestController
@RequestMapping("signrecord")
public class SignRecordController {
    @Autowired
    private SignRecordService signRecordService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("signrecord:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SignRecordEntity> signRecordList = signRecordService.queryList(query);
        int total = signRecordService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(signRecordList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("signrecord:info")
    public R info(@PathVariable("id") Long id) {
        SignRecordEntity signRecord = signRecordService.queryObject(id);
        return R.ok().put("signRecord", signRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("signrecord:save")
    public R save(@RequestBody SignRecordEntity signRecord) {
        signRecordService.save(signRecord);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("signrecord:update")
    public R update(@RequestBody SignRecordEntity signRecord) {
        signRecordService.update(signRecord);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("signrecord:delete")
    public R delete(@RequestBody Long[] ids) {
        signRecordService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<SignRecordEntity> list = signRecordService.queryList(params);
        return R.ok().put("list", list);
    }
}
