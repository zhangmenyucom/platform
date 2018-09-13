package com.platform.api;

import com.platform.entity.GiftExchangeRecordEntityVo;
import com.platform.service.ApiGiftExchangeRecordService;
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
 * @date 2018-09-13 15:20:44
 */
@RestController
@RequestMapping("giftexchangerecord")
public class ApiGiftExchangeRecordController {
    @Autowired
    private ApiGiftExchangeRecordService giftExchangeRecordService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("giftexchangerecord:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<GiftExchangeRecordEntityVo> giftExchangeRecordList = giftExchangeRecordService.queryList(query);
        int total = giftExchangeRecordService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(giftExchangeRecordList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("giftexchangerecord:info")
    public R info(@PathVariable("id") Long id) {
        GiftExchangeRecordEntityVo giftExchangeRecord = giftExchangeRecordService.queryObject(id);
        return R.ok().put("giftExchangeRecord", giftExchangeRecord);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("giftexchangerecord:update")
    public R update(@RequestBody GiftExchangeRecordEntityVo giftExchangeRecord) {
        giftExchangeRecordService.update(giftExchangeRecord);
        return R.ok();
    }


    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<GiftExchangeRecordEntityVo> list = giftExchangeRecordService.queryList(params);
        return R.ok().put("list", list);
    }
}
