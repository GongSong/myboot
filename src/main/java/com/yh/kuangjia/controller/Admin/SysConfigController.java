package com.yh.kuangjia.controller.Admin;


import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.models.SysConfig.SysConfigUpdate;
import com.yh.kuangjia.services.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.yh.kuangjia.core.BaseController;

/**
 * <p>
 * 平台配置 前端控制器
 * </p>
 *
 * @author 任性
 * @since 2019-10-29
 */
@RestController
@RequestMapping("api/config")
@Api("系统配置相关接口")
public class SysConfigController extends BaseController {

    @Autowired
    SysConfigService service;

    @ApiOperation(value = "获取类别列表")
    @RequestMapping(value = "list/{type}", method = RequestMethod.GET)
    public Result GetList(@PathVariable int type) {
        return service.GetList(type);
    }

    @ApiOperation(value = "更新")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result GetTypesInfoList(@RequestBody SysConfigUpdate dto) {
        return service.Update( dto);
    }
}
