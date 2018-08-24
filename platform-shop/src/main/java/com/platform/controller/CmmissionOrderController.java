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

import com.platform.entity.CmmissionOrderEntity;
import com.platform.service.CmmissionOrderService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-24 22:02:34
 */
@RestController
@RequestMapping("cmmissionorder")
public class CmmissionOrderController {
    @Autowired
    private CmmissionOrderService cmmissionOrderService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cmmissionorder:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<CmmissionOrderEntity> cmmissionOrderList = cmmissionOrderService.queryList(query);
        int total = cmmissionOrderService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(cmmissionOrderList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cmmissionorder:info")
    public R info(@PathVariable("id") Long id) {
        CmmissionOrderEntity cmmissionOrder = cmmissionOrderService.queryObject(id);
        return R.ok().put("cmmissionOrder", cmmissionOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cmmissionorder:save")
    public R save(@RequestBody CmmissionOrderEntity cmmissionOrder) {
        cmmissionOrderService.save(cmmissionOrder);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cmmissionorder:update")
    public R update(@RequestBody CmmissionOrderEntity cmmissionOrder) {
        cmmissionOrderService.update(cmmissionOrder);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cmmissionorder:delete")
    public R delete(@RequestBody Long[] ids) {
        cmmissionOrderService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<CmmissionOrderEntity> list = cmmissionOrderService.queryList(params);
        return R.ok().put("list", list);
    }
}
