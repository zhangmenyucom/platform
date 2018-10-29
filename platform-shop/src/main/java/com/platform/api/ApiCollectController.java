package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.service.ApiCollectService;
import com.platform.util.ApiBaseAction;
import com.platform.vo.CollectVo;
import com.platform.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "用户收藏")
@RestController
@RequestMapping("/api/{merchantId}/collect")
public class ApiCollectController extends ApiBaseAction {
    @Autowired
    private ApiCollectService collectService;

    /**
     * 获取用户收藏
     */
    @ApiOperation(value = "获取用户收藏")
    @GetMapping("list")
    public Object list(@PathVariable("merchantId") Long merchantId, @LoginUser UserVo loginUser, Integer typeId) {

        Map param = new HashMap(0);
        param.put("user_id", loginUser.getUserId());
        param.put("type_id", typeId);
        List<CollectVo> collectEntities = collectService.queryList(param,merchantId);

        return toResponsSuccess(collectEntities);
    }

    /**
     * 获取用户收藏
     */
    @ApiOperation(value = "添加取消收藏")
    @PostMapping("addordelete")
    public Object addordelete(@PathVariable("merchantId") Long merchantId,@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        Integer typeId = jsonParam.getInteger("typeId");
        Integer valueId = jsonParam.getInteger("valueId");

        Map param = new HashMap(0);
        param.put("user_id", loginUser.getUserId());
        param.put("type_id", typeId);
        param.put("value_id", valueId);
        List<CollectVo> collectEntities = collectService.queryList(param,merchantId);
        //
        Integer collectRes = null;
        String handleType = "add";
        if (null == collectEntities || collectEntities.size() < 1) {
            CollectVo collectEntity = new CollectVo();
            collectEntity.setAdd_time(new Date());
            collectEntity.setType_id(typeId);
            collectEntity.setValue_id(valueId);
            collectEntity.setIs_attention(0);
            collectEntity.setUser_id(loginUser.getUserId());
            collectEntity.setMerchantId(merchantId);
            //添加收藏
            collectRes = collectService.save(collectEntity);
        } else {
            //取消收藏
            collectRes = collectService.delete(collectEntities.get(0).getId());
            handleType = "delete";
        }

        if (collectRes > 0) {
            Map data = new HashMap(0);
            data.put("type", handleType);
            return toResponsSuccess(data);
        }
        return toResponsFail("操作失败");
    }
}