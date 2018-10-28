package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.service.ApiActivityService;
import com.platform.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.platform.common.ClientMenuEnum.clientMenuEnumMap;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2018-08-22 00:14:02
 */
@Api(tags = "菜单显示")
@RestController
@RequestMapping("/api/{merchantId}/menu")
public class ApiMenuController {

    @Autowired
    private ApiActivityService apiActivityService;

    /**
     * 查看列表
     */
    @IgnoreAuth
    @ApiOperation(value = "获取活动列表", response = Map.class)
    @GetMapping("map")
    public R list(@PathVariable("merchantId") Long merchantId) {
        return R.ok().put("data", clientMenuEnumMap);
    }
}
