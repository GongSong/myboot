package com.yh.kuangjia.controller.Admin;


import com.yh.kuangjia.base.ResultList;
import com.yh.kuangjia.models.AdminLog.AdminLogFilter;
import com.yh.kuangjia.services.AdminLogService;
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
 * @since 2019-10-29
 */
@RestController
@RequestMapping("api/log")
@Api("系统操作日志相关接口")
public class AdminLogController extends BaseController {

    @Autowired
    AdminLogService service;

    @ApiOperation(value = "分页列表")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResultList GetList(@RequestBody AdminLogFilter filter) {
        return service.GetPage(filter);
    }
}
