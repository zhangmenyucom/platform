package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.ActivityVo;
import com.platform.entity.ArticleVo;
import com.platform.entity.SeckillGoodsVo;
import com.platform.service.ApiActivityService;
import com.platform.service.ApiSeckillGoodsService;
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
@Api(tags = "秒杀活动")
@RestController
@RequestMapping("/api/{merchantId}/seckill")
public class ApiSecKillController {

    @Autowired
    private ApiSeckillGoodsService apiSeckillGoodsService;

    /**
     * 查看列表
     */
    @IgnoreAuth
    @ApiOperation(value = "获取秒杀商品列表", response = Map.class)
    @GetMapping("list")
    public R list(@PathVariable("merchantId") Long merchantId, @RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params, merchantId);
        List<SeckillGoodsVo> seckillGoodsVoList = apiSeckillGoodsService.queryList(query);
        int total = apiSeckillGoodsService.queryTotal(query, merchantId);
        PageUtils pageUtil = new PageUtils(seckillGoodsVoList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @IgnoreAuth
    @ApiOperation(value = "获取秒杀商品信息", response = Map.class)
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        SeckillGoodsVo seckillGoodsVo = apiSeckillGoodsService.queryObject(id);
        return R.ok().put("seckillGoodsVo", seckillGoodsVo);
    }
}
