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

import com.platform.entity.CommissionOrderEntity;
import com.platform.service.CommissionOrderService;
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
@RequestMapping("commissionorder")
public class CommissionOrderController {
    @Autowired
    private CommissionOrderService commissionOrderService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("commissionorder:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<CommissionOrderEntity> commissionOrderList = commissionOrderService.queryList(query);
        int total = commissionOrderService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(commissionOrderList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("commissionorder:info")
    public R info(@PathVariable("id") Long id) {
        CommissionOrderEntity commissionOrder = commissionOrderService.queryObject(id);
        return R.ok().put("commissionOrder", commissionOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("commissionorder:save")
    public R save(@RequestBody CommissionOrderEntity commissionOrder) {
        commissionOrderService.save(commissionOrder);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("commissionorder:update")
    public R update(@RequestBody CommissionOrderEntity commissionOrder) {
        commissionOrderService.update(commissionOrder);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("commissionorder:delete")
    public R delete(@RequestBody Long[] ids) {
        commissionOrderService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<CommissionOrderEntity> list = commissionOrderService.queryList(params);
        return R.ok().put("list", list);
    }
}
