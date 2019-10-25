package com.yh.kuangjia.controller;


import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.services.AdminRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("api/admin_role")
@Api("系统角色管理接口")
public class AdminRoleController extends BaseController {

    @Autowired
    AdminRoleService service;

    @ApiOperation(value = "角色管理界面展示")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Result getRoleInfoList() {
        return  service.getRoleList();
    }
}
