package com.platform.api;/**
 * ${author} on 2018/10/9.
 */

import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.entity.SysUserConfigEntity;
import com.platform.entity.SysUserEntity;
import com.platform.entity.UserVo;
import com.platform.entity.WithdrawOrderVo;
import com.platform.service.SysUserConfigService;
import com.platform.service.SysUserRoleService;
import com.platform.service.SysUserService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/10/9 11:01
 */
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
    @PostMapping("/save")
    public R save(@PathVariable("merchantId") Long merchantId, @RequestBody SysUserConfigEntity sysUserConfigEntity) {
        /**创建用户**/
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setDeptId(1L);
        sysUserEntity.setMobile(sysUserConfigEntity.getPhone());
        sysUserEntity.setStatus(1);
        sysUserService.save(sysUserEntity);
        /**关联角色**/
        List<Long> roleIdList = new ArrayList<>(1);
        roleIdList.add(5L);
        sysUserRoleService.saveOrUpdate(sysUserEntity.getUserId(), roleIdList);
        /**保存配置**/
        sysUserConfigEntity.setMerchantId(sysUserEntity.getUserId());
        sysUserConfigService.save(sysUserConfigEntity);
        return R.ok("已提交,待审核");
    }
}
