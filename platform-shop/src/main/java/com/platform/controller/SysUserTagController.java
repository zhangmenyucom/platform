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

import com.platform.entity.SysUserTagEntity;
import com.platform.service.SysUserTagService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-11-13 17:10:29
 */
@RestController
@RequestMapping("sysusertag")
public class SysUserTagController {
    @Autowired
    private SysUserTagService sysUserTagService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysusertag:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysUserTagEntity> sysUserTagList = sysUserTagService.queryList(query);
        int total = sysUserTagService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(sysUserTagList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysusertag:info")
    public R info(@PathVariable("id") Long id) {
        SysUserTagEntity sysUserTag = sysUserTagService.queryObject(id);
        return R.ok().put("sysUserTag", sysUserTag);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysusertag:save")
    public R save(@RequestBody SysUserTagEntity sysUserTag) {
        sysUserTagService.save(sysUserTag);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysusertag:update")
    public R update(@RequestBody SysUserTagEntity sysUserTag) {
        sysUserTagService.update(sysUserTag);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysusertag:delete")
    public R delete(@RequestBody Long[] ids) {
        sysUserTagService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<SysUserTagEntity> list = sysUserTagService.queryList(params);
        return R.ok().put("list", list);
    }
}
