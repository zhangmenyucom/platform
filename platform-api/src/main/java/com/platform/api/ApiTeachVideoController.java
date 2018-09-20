package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.TeachVideoVo;
import com.platform.service.ApiTeachVideoService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-26 18:25:17
 */
@Api(tags = "导师视频")
@RestController
@RequestMapping("/api/{merchantId}/teachvideo")
public class ApiTeachVideoController {
    @Autowired
    private ApiTeachVideoService teachVideoService;

    /**
     * 查看列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    @ApiOperation(value = "导师视频列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<TeachVideoVo> teachVideoList = teachVideoService.queryList(query);
        int total = teachVideoService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(teachVideoList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @IgnoreAuth
    @RequestMapping("/info/{id}")
    @ApiOperation(value = "查看")
    public R info(@PathVariable("id") Long id) {
        TeachVideoVo teachVideo = teachVideoService.queryObject(id);
        return R.ok().put("teachVideo", teachVideo);
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ApiOperation(value = "导师视频列表所有")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<TeachVideoVo> list = teachVideoService.queryList(params);
        return R.ok().put("list", list);
    }
}
