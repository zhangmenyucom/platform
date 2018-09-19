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

import com.platform.entity.SmsLogEntity;
import com.platform.service.SmsLogService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-09 04:53:06
 */
@RestController
@RequestMapping("smslog")
public class SmsLogController {
    @Autowired
    private SmsLogService smsLogService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("smslog:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SmsLogEntity> smsLogList = smsLogService.queryList(query);
        int total = smsLogService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(smsLogList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("smslog:info")
    public R info(@PathVariable("id") Long id) {
        SmsLogEntity smsLog = smsLogService.queryObject(id);
        return R.ok().put("smsLog", smsLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("smslog:save")
    public R save(@RequestBody SmsLogEntity smsLog) {
        smsLogService.save(smsLog);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("smslog:update")
    public R update(@RequestBody SmsLogEntity smsLog) {
        smsLogService.update(smsLog);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("smslog:delete")
    public R delete(@RequestBody Integer[] ids) {
        smsLogService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<SmsLogEntity> list = smsLogService.queryList(params);
        return R.ok().put("list", list);
    }
}
