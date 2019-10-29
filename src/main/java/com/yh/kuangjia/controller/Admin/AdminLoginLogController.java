package com.yh.kuangjia.controller.Admin;

import com.yh.kuangjia.base.ResultList;
import com.yh.kuangjia.core.BaseController;
import com.yh.kuangjia.models.AdminLoginLog.AdminLoginLogFilter;
import com.yh.kuangjia.services.AdminLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login_log")
@Api("后台登录日志接口")
public class AdminLoginLogController extends BaseController {

    @Autowired
    AdminLoginLogService service;

    @ApiOperation(value = "分页列表")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResultList GetList(@RequestBody AdminLoginLogFilter filter) {
        return service.GetPage(filter);
    }
}
