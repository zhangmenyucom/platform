package com.platform.api;

import java.util.List;
import java.util.Map;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.CmmissionOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.service.CmmissionOrderService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-25 14:20:46
 */
@Api(tags = "佣金")
@RestController
@RequestMapping("/api/cmmissionorder")
public class ApiCmmissionOrderController {
    @Autowired
    private CmmissionOrderService cmmissionOrderService;

    /**
     * 查看列表
     */
    @IgnoreAuth
    @ApiOperation(value = "获取列表")
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<CmmissionOrderVo> cmmissionOrderList = cmmissionOrderService.queryList(query);
        int total = cmmissionOrderService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(cmmissionOrderList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @IgnoreAuth
    @ApiOperation(value = "查看信息")
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CmmissionOrderVo cmmissionOrder = cmmissionOrderService.queryObject(id);
        return R.ok().put("cmmissionOrder", cmmissionOrder);
    }

    /**
     * 保存
     */
    @IgnoreAuth
    @ApiOperation(value = "保存")
    @RequestMapping("/save")
    public R save(@RequestBody CmmissionOrderVo cmmissionOrder) {
        cmmissionOrderService.save(cmmissionOrder);
        return R.ok();
    }

    /**
     * 修改
     */
    @IgnoreAuth
    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    public R update(@RequestBody CmmissionOrderVo cmmissionOrder) {
        cmmissionOrderService.update(cmmissionOrder);
        return R.ok();
    }

    /**
     * 删除
     */
    @IgnoreAuth
    @ApiOperation(value = "删除")
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        cmmissionOrderService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @IgnoreAuth
    @ApiOperation(value = "查看所有列表")
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<CmmissionOrderVo> list = cmmissionOrderService.queryList(params);
        return R.ok().put("list", list);
    }
}
