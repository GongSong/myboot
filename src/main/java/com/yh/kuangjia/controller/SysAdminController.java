package com.yh.kuangjia.controller;


import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.core.annotation.IgnoreLogin;
import com.yh.kuangjia.models.AdminUser.AdminUserLogin;
import com.yh.kuangjia.services.ISysAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yh.kuangjia.core.BaseController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 任性
 * @since 2019-10-23
 */
@RestController
@RequestMapping("api/admin")
public class SysAdminController extends BaseController {

    @Autowired
    ISysAdminService service;

    @IgnoreLogin
    @ApiOperation(value = "登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestBody AdminUserLogin dto) {
        return service.login(dto);
    }


    @ApiOperation(value = "登录")
    @RequestMapping(value = "config", method = RequestMethod.GET)
    public Result config() {
        return service.config(this.GetTokenAdmin().getAdminId());
    }


}
