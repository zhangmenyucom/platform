package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.ArticleVo;
import com.platform.service.ApiArticleService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @date 2018-08-22 00:14:02
 */
@Api(tags = "媒体文章")
@RestController
@RequestMapping("/api/article")
public class ApiArticleController {
    @Autowired
    private ApiArticleService apiArticleService;

    /**
     * 查看列表
     */
    @IgnoreAuth
    @ApiOperation(value = "获取文章列表", response = Map.class)
    @GetMapping("list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ArticleVo> articleList = apiArticleService.queryList(query);
        int total = apiArticleService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(articleList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @IgnoreAuth
    @ApiOperation(value = "获取文章信息", response = Map.class)
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        ArticleVo article = apiArticleService.queryObject(id);
        return R.ok().put("article", article);
    }

    /**
     * 查看所有列表
     */
    @IgnoreAuth
    @ApiOperation(value = "获取所有文章列表", response = Map.class)
    @GetMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<ArticleVo> list = apiArticleService.queryList(params);
        return R.ok().put("list", list);
    }
}
