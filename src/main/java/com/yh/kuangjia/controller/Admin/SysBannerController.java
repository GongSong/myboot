package com.yh.kuangjia.controller.Admin;


import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.base.ResultList;
import com.yh.kuangjia.dao.SysBannerMapper;
import com.yh.kuangjia.models.SingleID;
import com.yh.kuangjia.models.SysBanner.SysBannerAdd;
import com.yh.kuangjia.models.SysBanner.SysBannerEdit;
import com.yh.kuangjia.models.SysBanner.SysBannerFilter;
import com.yh.kuangjia.services.SysBannerService;
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
 * 前端控制器
 * </p>
 *
 * @author 任性
 * @since 2019-10-28
 */
@RestController
@RequestMapping("api/banner")
@Api("系统轮播相关接口")
public class SysBannerController extends BaseController {

    @Autowired
    SysBannerService service;

    @ApiOperation(value = "列表")
    @RequestMapping(value = "manage", method = RequestMethod.POST)
    public ResultList Manage(@RequestBody SysBannerFilter filter) {
        return service.GetPage(filter);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result Delete(@RequestBody SingleID dto) {
        return service.Delete(this.GetTokenAdmin().getAdminId(),dto);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result Add(@RequestBody SysBannerAdd dto) {
        return service.Add(this.GetTokenAdmin().getAdminId(),dto);
    }

    @ApiOperation(value = "更新")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result Update(@RequestBody SysBannerEdit dto) {
        return service.Edit(this.GetTokenAdmin().getAdminId(),dto);
    }

    @ApiOperation(value = "更新显示状态")
    @RequestMapping(value = "is_show", method = RequestMethod.POST)
    public Result UpdateIsShow(@RequestBody SingleID dto) {
        return service.Update(this.GetTokenAdmin().getAdminId(),dto);
    }
}
