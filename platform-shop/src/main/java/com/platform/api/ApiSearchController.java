package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.service.ApiKeywordsService;
import com.platform.service.ApiSearchHistoryService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.Query;
import com.platform.vo.KeywordsVo;
import com.platform.vo.SearchHistoryVo;
import com.platform.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API登录授权
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-03-23 15:31
 */
@Api(tags = "商品搜索")
@RestController
@RequestMapping("/api/{merchantId}/search")
public class ApiSearchController extends ApiBaseAction {
    @Autowired
    private ApiKeywordsService keywordsService;
    @Autowired
    private ApiSearchHistoryService searchHistoryService;

    /**
     * 　　index
     */
    @ApiOperation(value = "搜索商品列表")
    @GetMapping("index")
    public Object index(@PathVariable("merchantId") Long merchantId, @LoginUser UserVo loginUser) {
        Map<String, Object> resultObj = new HashMap(0);
        Map param = new HashMap(0);
        param.put("is_default", 1);
        param.put("page", 1);
        param.put("limit", 1);
        param.put("sidx", "id");
        param.put("order", "asc");
        List<KeywordsVo> keywordsEntityList = keywordsService.queryList(param,merchantId);
        //取出输入框默认的关键词
        KeywordsVo defaultKeyword = null != keywordsEntityList && keywordsEntityList.size() > 0 ? keywordsEntityList.get(0) : null;
        //取出热闹关键词
        param = new HashMap(0);
        param.put("fields", "distinct keyword,is_hot");
        param.put("page", 1);
        param.put("limit", 10);
        param.put("sidx", "id");
        param.put("order", "asc");
        Query query = new Query(param,merchantId);
        List<Map> hotKeywordList = keywordsService.hotKeywordList(query);
        //
        param = new HashMap(0);
        param.put("user_id", loginUser.getUserId());
        param.put("fields", "distinct keyword");
        param.put("page", 1);
        param.put("limit", 10);
        param.put("sidx", "id");
        param.put("order", "asc");
        List<SearchHistoryVo> historyVoList = searchHistoryService.queryList(param,merchantId);
        String[] historyKeywordList = new String[historyVoList.size()];
        if (null != historyVoList) {
            int i = 0;
            for (SearchHistoryVo historyVo : historyVoList) {
                historyKeywordList[i] = historyVo.getKeyword();
                i++;
            }
        }
        //
        resultObj.put("defaultKeyword", defaultKeyword);
        resultObj.put("historyKeywordList", historyKeywordList);
        resultObj.put("hotKeywordList", hotKeywordList);
        return toResponsSuccess(resultObj);
    }

    /**
     * 　　helper
     */
    @ApiOperation(value = "搜索商品")
    @ApiImplicitParams({@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "path", required = true)})
    @IgnoreAuth
    @GetMapping("helper")
    public Object helper(@PathVariable("merchantId") Long merchantId,String keyword) {
        Map param = new HashMap(0);
        param.put("fields", "distinct keyword");
        param.put("keyword", keyword);
        param.put("limit", 10);
        param.put("offset", 0);
        List<KeywordsVo> keywords = keywordsService.queryList(param,merchantId);
        String[] keys = new String[keywords.size()];
        if (null != keywords) {
            int i = 0;
            for (KeywordsVo keywordsVo : keywords) {
                keys[i] = keywordsVo.getKeyword();
                i++;
            }
        }
        //
        return toResponsSuccess(keys);
    }

    /**
     * 　　clearhistory
     */
    @PostMapping("clearhistory")
    public Object clearhistory(@LoginUser UserVo loginUser) {
        searchHistoryService.deleteByUserId(loginUser.getUserId());
        //
        return toResponsSuccess("");
    }
}