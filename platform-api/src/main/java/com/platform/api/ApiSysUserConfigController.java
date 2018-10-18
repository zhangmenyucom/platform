package com.platform.api;/**
 * ${author} on 2018/10/9.
 */

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.SysUserConfigEntity;
import com.platform.entity.SysUserEntity;
import com.platform.service.SysUserConfigService;
import com.platform.service.SysUserRoleService;
import com.platform.service.SysUserService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.JsonUtil;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/10/9 11:01
 */
@Slf4j
@RestController
@RequestMapping("/api/{merchantId}/sysuserconfig")
public class ApiSysUserConfigController extends ApiBaseAction {

    @Autowired
    private SysUserConfigService sysUserConfigService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 保存
     */
    @IgnoreAuth
    @GetMapping("/save")
    public R save(@PathVariable("merchantId") Long merchantId,
                  @RequestParam("appId") String appId,
                  @RequestParam("secret") String secret,
                  @RequestParam("mchId") String mchId,
                  @RequestParam("paySignKey") String paySignKey,
                  @RequestParam("certAddress") String certAddress,
                  @RequestParam("storeName") String storeName,
                  @RequestParam("storeAddress") String storeAddress,
                  @RequestParam("phone") String phone) throws UnsupportedEncodingException {
        SysUserConfigEntity sysUserConfigEntity = new SysUserConfigEntity();
        sysUserConfigEntity.setAppId(appId)
                .setCertAddress(certAddress)
                .setMchId(mchId)
                .setPaySignKey(paySignKey)
                .setSecret(secret)
                .setStoreName(storeName)
                .setStoreAddress(storeAddress)
                .setPhone(phone)
                .setMerchantId(merchantId);
        log.info("sysUserConfigEntity", JsonUtil.getJsonByObj(sysUserConfigEntity));
        /**创建用户**/
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setDeptId(1L);
        sysUserEntity.setUsername(sysUserConfigEntity.getPhone());
        sysUserEntity.setMobile(sysUserConfigEntity.getPhone());
        sysUserEntity.setStatus(1);
        sysUserEntity.setCreateUserId(1L);
        /**关联角色**/
        List<Long> roleIdList = new ArrayList<>(1);
        roleIdList.add(5L);
        sysUserEntity.setRoleIdList(roleIdList);
        sysUserService.save(sysUserEntity);
        sysUserRoleService.saveOrUpdate(sysUserEntity.getUserId(), roleIdList);
        /**保存配置**/
        sysUserConfigEntity.setMerchantId(sysUserEntity.getUserId());
        sysUserConfigService.save(sysUserConfigEntity);
        return R.ok("已提交,待审核");
    }

    /**
     * 查看列表
     */
    @IgnoreAuth
    @ApiOperation(value = "获取商家信息列表", response = Map.class)
    @GetMapping("list")
    public R list(@PathVariable("merchantId") Long merchantId, @RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params, merchantId);
        List<SysUserConfigEntity> sysUserConfigList = sysUserConfigService.queryAll(query);
        int total = sysUserConfigService.queryTotalAll(query);
        PageUtils pageUtil = new PageUtils(sysUserConfigList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }
}
