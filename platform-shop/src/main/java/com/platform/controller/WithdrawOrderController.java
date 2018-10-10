package com.platform.controller;

import com.platform.entity.TransferReqBean;
import com.platform.entity.WithdrawOrderEntity;
import com.platform.service.UserService;
import com.platform.service.WithdrawOrderService;
import com.platform.service.impl.TransferService;
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
 * @date 2018-08-24 22:02:34
 */
@RestController
@RequestMapping("withdraworder")
public class WithdrawOrderController {
    @Autowired
    private WithdrawOrderService withdrawOrderService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("withdraworder:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<WithdrawOrderEntity> withdrawOrderList = withdrawOrderService.queryList(query);
        int total = withdrawOrderService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(withdrawOrderList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("withdraworder:info")
    public R info(@PathVariable("id") Long id) {
        WithdrawOrderEntity withdrawOrder = withdrawOrderService.queryObject(id);
        return R.ok().put("withdrawOrder", withdrawOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("withdraworder:save")
    public R save(@RequestBody WithdrawOrderEntity withdrawOrder) {
        withdrawOrderService.save(withdrawOrder);
        return R.ok();
    }

    /**
     * 审核提现
     */
    @RequestMapping("/update")
    @RequiresPermissions("withdraworder:update")
    public R update(@RequestBody WithdrawOrderEntity withdrawOrder) {
        withdrawOrderService.auditingWithdrawOrder(withdrawOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("withdraworder:delete")
    public R delete(@RequestBody Long[] ids) {
        withdrawOrderService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<WithdrawOrderEntity> list = withdrawOrderService.queryList(params);
        return R.ok().put("list", list);
    }
}
