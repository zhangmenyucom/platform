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

import com.platform.entity.SysUserConfigEntity;
import com.platform.service.SysUserConfigService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-22 17:58:46
 */
@RestController
@RequestMapping("/sys/userconfig")
public class SysUserConfigController extends AbstractController{
    @Autowired
    private SysUserConfigService sysUserConfigService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sysuserconfig:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysUserConfigEntity> sysUserConfigList = sysUserConfigService.queryList(query);
        int total = sysUserConfigService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(sysUserConfigList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sysuserconfig:info")
    public R info(@PathVariable("id") Long id) {
        SysUserConfigEntity sysUserConfig = sysUserConfigService.queryObject(id);
        return R.ok().put("sysUserConfig", sysUserConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysuserconfig:save")
    public R save(@RequestBody SysUserConfigEntity sysUserConfig) {
        sysUserConfigService.save(sysUserConfig);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysuserconfig:update")
    public R update(@RequestBody SysUserConfigEntity sysUserConfig) {
        sysUserConfigService.update(sysUserConfig);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysuserconfig:delete")
    public R delete(@RequestBody Long[] ids) {
        sysUserConfigService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<SysUserConfigEntity> list = sysUserConfigService.queryList(params);
        return R.ok().put("list", list);
    }
}
