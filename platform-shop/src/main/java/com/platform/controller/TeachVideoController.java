package com.platform.controller;

import com.platform.entity.TeachVideoEntity;
import com.platform.service.TeachVideoService;
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
 * @date 2018-08-26 18:25:17
 */
@RestController
@RequestMapping("teachvideo")
public class TeachVideoController {
    @Autowired
    private TeachVideoService teachVideoService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("teachvideo:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<TeachVideoEntity> teachVideoList = teachVideoService.queryList(query);
        int total = teachVideoService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(teachVideoList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("teachvideo:info")
    public R info(@PathVariable("id") Long id) {
        TeachVideoEntity teachVideo = teachVideoService.queryObject(id);
        return R.ok().put("teachVideo", teachVideo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("teachvideo:save")
    public R save(@RequestBody TeachVideoEntity teachVideo) {
        teachVideoService.save(teachVideo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("teachvideo:update")
    public R update(@RequestBody TeachVideoEntity teachVideo) {
        teachVideoService.update(teachVideo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("teachvideo:delete")
    public R delete(@RequestBody Long[] ids) {
        teachVideoService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<TeachVideoEntity> list = teachVideoService.queryList(params);
        return R.ok().put("list", list);
    }
}
