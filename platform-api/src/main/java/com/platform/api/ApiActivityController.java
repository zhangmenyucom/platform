package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.ActivityVo;
import com.platform.service.ApiActivityService;
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
 * @date 2018-08-22 00:14:02
 */
@Api(tags = "活动咨询")
@RestController
@RequestMapping("/api/{merchantId}/activity")
public class ApiActivityController {

    @Autowired
    private ApiActivityService apiActivityService;

    /**
     * 查看列表
     */
    @IgnoreAuth
    @ApiOperation(value = "获取活动列表", response = Map.class)
    @GetMapping("list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ActivityVo> activityList = apiActivityService.queryList(query);
        int total = apiActivityService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(activityList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @IgnoreAuth
    @ApiOperation(value = "活动详情", response = Map.class)
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        ActivityVo activity = apiActivityService.queryObject(id);
        return R.ok().put("activity", activity);
    }

    /**
     * 查看所有列表
     */
    @IgnoreAuth
    @ApiOperation(value = "获取所有活动", response = Map.class)
    @GetMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<ActivityVo> list = apiActivityService.queryList(params);
        return R.ok().put("list", list);
    }
}
