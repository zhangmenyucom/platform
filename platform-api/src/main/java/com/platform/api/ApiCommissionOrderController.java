package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.CommissionOrderVo;
import com.platform.service.ApiCommissionOrderService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-25 14:20:46
 */
@Api(tags = "佣金")
@RestController
@RequestMapping("/api/commissionorder")
public class ApiCommissionOrderController {
    @Autowired
    private ApiCommissionOrderService commissionOrderService;

    /**
     * 查看列表
     */
    @IgnoreAuth
    @ApiOperation(value = "获取列表")
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<CommissionOrderVo> commissionOrderList = commissionOrderService.queryDetailList(query);
        int total = commissionOrderService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(commissionOrderList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @IgnoreAuth
    @ApiOperation(value = "查看信息")
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CommissionOrderVo commissionOrder = commissionOrderService.queryObject(id);
        return R.ok().put("commissionOrder", commissionOrder);
    }

    /**
     * 保存
     */
    @IgnoreAuth
    @ApiOperation(value = "保存")
    @RequestMapping("/save")
    public R save(@RequestBody CommissionOrderVo commissionOrder) {
        commissionOrderService.save(commissionOrder);
        return R.ok();
    }

    /**
     * 修改
     */
    @IgnoreAuth
    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    public R update(@RequestBody CommissionOrderVo commissionOrder) {
        commissionOrderService.update(commissionOrder);
        return R.ok();
    }

    /**
     * 删除
     */
    @IgnoreAuth
    @ApiOperation(value = "删除")
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        commissionOrderService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @IgnoreAuth
    @ApiOperation(value = "查看所有列表")
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<CommissionOrderVo> list = commissionOrderService.queryList(params);
        return R.ok().put("list", list);
    }
}
