package com.yh.kuangjia.controller.Admin;


import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.base.ResultList;
import com.yh.kuangjia.core.annotation.IgnoreLogin;
import com.yh.kuangjia.models.AdminUser.AdminUserAdd;
import com.yh.kuangjia.models.AdminUser.AdminUserFilter;
import com.yh.kuangjia.models.AdminUser.AdminUserLogin;
import com.yh.kuangjia.models.AdminUser.AdminUserUpdate;
import com.yh.kuangjia.services.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.yh.kuangjia.core.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
@RestController
@RequestMapping("api/admin_user")
@Api("系统后台用户登录接口")
public class AdminUserController extends BaseController {

    @Autowired
    AdminUserService service;

    @IgnoreLogin
    @ApiOperation(value = "登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    private Result Login(@RequestBody AdminUserLogin login) {
        return service.Login(login);
    }

    @ApiOperation(value = "获取用户菜单")
    @RequestMapping(value = "menu_right", method = RequestMethod.GET)
    private Result GetAdminUserMenuRightList() {
        return service.GetAdminUserMenuRightList(this.GetTokenAdmin().getAdminId());
    }

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "my_info",method = RequestMethod.GET)
    public Result getUserInfo() {
        return service.getUserInfo(this.GetTokenAdmin().getAdminId());
    }

    @ApiOperation(value = "获取用户列表")
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public ResultList getList(@RequestBody AdminUserFilter filter) {
        return service.getUserList(filter);
    }

    @ApiOperation(value = "新增用户")
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Result addUser(@RequestBody AdminUserAdd dto) {
        return service.addUser(this.GetTokenAdmin().getAdminId(),dto);
    }

    @ApiOperation(value = "编辑用户")
    @RequestMapping(value = "get_info/{admin_id}",method = RequestMethod.GET)
    public Result getInfo(@PathVariable("admin_id")Integer admin_id) {
        return service.getInfo(admin_id);
    }

    @ApiOperation(value = "编辑用户")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Result updateUser(@RequestBody AdminUserUpdate dto) {
        return service.updateUser(dto);
    }

    @ApiOperation(value = "禁用操作")
    @RequestMapping(value = "disabled/{admin_id}",method = RequestMethod.GET)
    public Result updateIsDisabled(@PathVariable("admin_id")Integer admin_id) {
        return  service.updateIsDisabled(admin_id);
    }

    @ApiOperation(value = "删除操作")
    @RequestMapping(value = "delete/{admin_id}",method = RequestMethod.GET)
    public Result deleteUser(@PathVariable("admin_id")Integer admin_id) {
        return Result.success(service.removeById(admin_id));
    }
}
