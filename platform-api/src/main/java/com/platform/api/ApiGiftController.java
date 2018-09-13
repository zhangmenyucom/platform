package com.platform.api;

import java.util.List;
import java.util.Map;

import com.platform.service.ApiGiftService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.GiftEntityVo;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-09-13 15:20:44
 */
@RestController
@RequestMapping("gift")
public class ApiGiftController {
    @Autowired
    private ApiGiftService giftService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("gift:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<GiftEntityVo> giftList = giftService.queryList(query);
        int total = giftService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(giftList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("gift:info")
    public R info(@PathVariable("id") Long id) {
        GiftEntityVo gift = giftService.queryObject(id);
        return R.ok().put("gift", gift);
    }


    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<GiftEntityVo> list = giftService.queryList(params);
        return R.ok().put("list", list);
    }
}
