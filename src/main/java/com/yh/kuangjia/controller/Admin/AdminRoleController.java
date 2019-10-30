package com.yh.kuangjia.controller.Admin;


import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.models.AdminRight.AdminRightAdd;
import com.yh.kuangjia.models.AdminRole.AdminRoleUpdate;
import com.yh.kuangjia.services.AdminRightService;
import com.yh.kuangjia.services.AdminRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.yh.kuangjia.core.BaseController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
@RestController
@RequestMapping("api/admin_role")
@Api("系统角色管理接口")
public class AdminRoleController extends BaseController {

    @Autowired
    AdminRoleService service;
    @Autowired
    AdminRightService adminRightService;

    @ApiOperation(value = "角色管理界面展示")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result getRoleInfoList() {
        return service.getRoleList();
    }

    @ApiOperation(value = "删除角色")
    @RequestMapping(value = "delete/{role_id}",method = RequestMethod.GET)
    public Result delRole(@PathVariable int role_id) {
        return service.delRole(this.GetTokenAdmin().getAdminId(),role_id);
    }

    @ApiOperation(value = "修改角色")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Result updateRole(@RequestBody AdminRoleUpdate dto) {
        return service.updateRole(this.GetTokenAdmin().getAdminId(),dto);
    }

    @ApiOperation(value = "权限设置保存")
    @RequestMapping(value = "assign_right",method = RequestMethod.POST)
    public Result assignRight(@RequestBody AdminRightAdd dto){
        return adminRightService.setRight(dto);
    }
}
