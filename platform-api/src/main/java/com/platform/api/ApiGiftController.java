package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.GiftEntityVo;
import com.platform.service.ApiGiftService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
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
 * @date 2018-09-13 15:20:44
 */
@RestController
@RequestMapping("/api/{merchantId}/gift")
public class ApiGiftController {
    @Autowired
    private ApiGiftService giftService;

    /**
     * 查看列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@PathVariable("merchantId") Long merchantId,@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params,merchantId);
        List<GiftEntityVo> giftList = giftService.queryList(query);
        int total = giftService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(giftList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @IgnoreAuth
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        GiftEntityVo gift = giftService.queryObject(id);
        return R.ok().put("gift", gift);
    }


    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@PathVariable("merchantId") Long merchantId,@RequestParam Map<String, Object> params) {
        List<GiftEntityVo> list = giftService.queryList(params,merchantId);
        return R.ok().put("list", list);
    }
}
