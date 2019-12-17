package com.yh.kuangjia.controller.Admin;


import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.base.Tree.Result1;
import com.yh.kuangjia.models.AdminDept.AdminDeptAdd;
import com.yh.kuangjia.models.AdminDept.AdminDeptEdit;
import com.yh.kuangjia.models.SingleID;
import com.yh.kuangjia.services.AdminDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.yh.kuangjia.core.BaseController;

/**
 * <p>
 * 管理用户部门 前端控制器
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
@RestController
@RequestMapping("api/dept")
@Api("系统部门管理相关接口")
public class AdminDeptController extends BaseController {

    @Autowired
    AdminDeptService service;

    @ApiOperation(value = "管理列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result list() {
        return service.Devlist();
    }

    @ApiOperation(value = "管理列表")
    @RequestMapping(value = "tree", method = RequestMethod.GET)
    public Result tree() {
        return service.tree();
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result Delete(@RequestBody SingleID dto) {
        return service.Delete(this.GetTokenAdmin().getAdminId(), dto);
    }

    @ApiOperation(value = "查看部门")
    @RequestMapping(value = "view/{dept_id}", method = RequestMethod.GET)
    public Result view(@PathVariable("dept_id") int dept_id) {
        return service.view(dept_id);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result Add(@RequestBody AdminDeptAdd dto) {
        return service.Add(this.GetTokenAdmin().getAdminId(), dto);
    }

    @ApiOperation(value = "编辑页面的展示")
    @RequestMapping(value = "get_info/{dept_id}", method = RequestMethod.GET)
    public Result getInfo(@PathVariable("dept_id") int dept_id) {
        return service.getInfo(dept_id);
    }

    @ApiOperation(value = "更新")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result Update(@RequestBody AdminDeptEdit dto) {
        return service.Update(this.GetTokenAdmin().getAdminId(),dto);
    }

}
