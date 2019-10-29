package com.yh.kuangjia.controller.Admin;


import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.core.annotation.ParamCheck;
import com.yh.kuangjia.models.SingleID;
import com.yh.kuangjia.models.SysDictionary.SysDictionaryAdd;
import com.yh.kuangjia.models.SysDictionary.SysDictionaryList;
import com.yh.kuangjia.models.SysDictionary.SysDictionaryUpdate;
import com.yh.kuangjia.services.SysDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.yh.kuangjia.core.BaseController;

import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author 任性
 * @since 2019-10-29
 */
@RestController
@RequestMapping("api/dict")
@Api("系统数据字典相关接口")
public class SysDictionaryController extends BaseController {

    @Autowired
    SysDictionaryService service;

    @ApiOperation(value = "获取类别详情列表")
    @RequestMapping(value = "list/{dicttype}", method = RequestMethod.GET)
    public Result GetTypesInfoList(@PathVariable("dicttype") int dicttype) {
        return service.GetDevList(dicttype);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result Delete(@RequestBody SingleID dto) {
        return service.Delete(dto);
    }


    @ApiOperation(value = "添加")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result Add(@ParamCheck @RequestBody SysDictionaryAdd dto) {
        return service.Add(this.GetTokenAdmin().getAdminId(), dto);
    }


    @ApiOperation(value = "更新")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result Update(@ParamCheck @RequestBody SysDictionaryUpdate dto) {
        return service.Update(this.GetTokenAdmin().getAdminId(), dto);
    }
}
