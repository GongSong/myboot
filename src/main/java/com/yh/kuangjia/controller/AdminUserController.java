package com.yh.kuangjia.controller;


import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.base.ResultList;
import com.yh.kuangjia.core.annotation.IgnoreLogin;
import com.yh.kuangjia.models.AdminUser.AdminUserAdd;
import com.yh.kuangjia.models.AdminUser.AdminUserFilter;
import com.yh.kuangjia.models.AdminUser.AdminUserLogin;
import com.yh.kuangjia.services.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
}
