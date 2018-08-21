package com.platform.controller;

import com.platform.entity.ArticleEntity;
import com.platform.service.ArticleService;
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
 * @date 2018-08-22 00:14:02
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("article:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ArticleEntity> articleList = articleService.queryList(query);
        int total = articleService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(articleList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("article:info")
    public R info(@PathVariable("id") Long id) {
        ArticleEntity article = articleService.queryObject(id);
        return R.ok().put("article", article);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("article:save")
    public R save(@RequestBody ArticleEntity article) {
        articleService.save(article);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("article:update")
    public R update(@RequestBody ArticleEntity article) {
        articleService.update(article);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("article:delete")
    public R delete(@RequestBody Long[] ids) {
        articleService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<ArticleEntity> list = articleService.queryList(params);
        return R.ok().put("list", list);
    }
}
