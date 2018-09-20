package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.TopicVo;
import com.platform.service.ApiTopicService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@RestController
@RequestMapping("/api/{merchantId}/topic")
public class ApiTopicController extends ApiBaseAction {
    @Autowired
    private ApiTopicService topicService;

    /**
     */
    @IgnoreAuth
    @GetMapping("list")
    public Object list(@PathVariable("merchantId") Long merchantId, @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map param = new HashMap(0);
        param.put("page", page);
        param.put("limit", size);
        param.put("sidx", "id");
        param.put("order", "desc");
        param.put("fields", "id, title, price_info, scene_pic_url,subtitle");
        //查询列表数据
        Query query = new Query(param,merchantId);
        List<TopicVo> topicEntities = topicService.queryList(query);
        int total = topicService.queryTotal(query);
        ApiPageUtils pageUtil = new ApiPageUtils(topicEntities, total, query.getLimit(), query.getPage());
        return toResponsSuccess(pageUtil);
    }

    /**
     */
    @IgnoreAuth
    @GetMapping("detail")
    public Object detail(Long id) {
        TopicVo topicEntity = topicService.queryObject(id);
        return toResponsSuccess(topicEntity);
    }

    /**
     */
    @IgnoreAuth
    @GetMapping("related")
    public Object related(@PathVariable("merchantId") Long merchantId) {
        Map param = new HashMap(0);
        param.put("limit", 4);
        List<TopicVo> topicEntities = topicService.queryList(param,merchantId);
        return toResponsSuccess(topicEntities);
    }
}