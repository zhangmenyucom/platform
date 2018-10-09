package com.platform.api;

import com.platform.annotation.LoginUser;
import com.platform.entity.UserVo;
import com.platform.entity.WithdrawOrderVo;
import com.platform.service.ApiWithdrawOrderService;
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
@Api(tags = "提现")
@RestController
@RequestMapping("/api/{merchantId}/withdraworder")
public class ApiWithdrawOrderController {
    @Autowired
    private ApiWithdrawOrderService withdrawOrderService;

    /**
     * 查看列表
     */
    @ApiOperation(value = "查看列表")
    @RequestMapping("/list")
    public R list(@PathVariable("merchantId") Long merchantId,@RequestParam Map<String, Object> params) {
        /**查询列表数据**/
        Query query = new Query(params,merchantId);
        List<WithdrawOrderVo> withdrawOrderList = withdrawOrderService.queryList(query);
        int total = withdrawOrderService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(withdrawOrderList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @ApiOperation(value = "查看")
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        WithdrawOrderVo withdrawOrder = withdrawOrderService.queryObject(id);
        return R.ok().put("withdrawOrder", withdrawOrder);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@PathVariable("merchantId") Long merchantId,@RequestBody WithdrawOrderVo withdrawOrder,@LoginUser UserVo loginUser) {
        withdrawOrder.setMerchantId(merchantId);
        withdrawOrder.setUserId(loginUser.getUserId());
        withdrawOrder.setAccountType(0);
        withdrawOrder.setWithdrawAccount("微信");
        withdrawOrder.setStatus(0);
        withdrawOrder.setComment("提交审核");
        withdrawOrderService.save(withdrawOrder);
        return R.ok("已提交,待审核");
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    public R update(@RequestBody WithdrawOrderVo withdrawOrder) {
        withdrawOrderService.update(withdrawOrder);
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        withdrawOrderService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @ApiOperation(value = "查看所有列表")
    @RequestMapping("/queryAll")
    public R queryAll(@PathVariable("merchantId") Long merchantId,@RequestParam Map<String, Object> params) {
        List<WithdrawOrderVo> list = withdrawOrderService.queryList(params,merchantId);
        return R.ok().put("list", list);
    }
}
