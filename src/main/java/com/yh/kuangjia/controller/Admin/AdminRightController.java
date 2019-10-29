package com.yh.kuangjia.controller.Admin;


import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.models.AdminRight.AdminRightDevAdd;
import com.yh.kuangjia.models.AdminRightGroup.AdminGroupRightUpdate;
import com.yh.kuangjia.models.AdminRightGroup.AdminRightGroupAdd;
import com.yh.kuangjia.models.AdminRightGroup.AdminRightGroupUpdate;
import com.yh.kuangjia.services.AdminRightGroupService;
import com.yh.kuangjia.services.AdminRightService;
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
 * @since 2019-10-28
 */
@RestController
@RequestMapping("api/admin_right")
public class AdminRightController extends BaseController {

    @Autowired
    AdminRightService service;
    @Autowired
    AdminRightGroupService adminRightGroupService;

    @ApiOperation(value = "权限设置")
    @RequestMapping(value = "tree/{role_id}", method = RequestMethod.GET)
    public Result getRight(@PathVariable("role_id") Integer role_id) {
        return service.getRight(role_id);
    }


    @ApiOperation(value = "获取菜单接口")
    @RequestMapping(value = "controllers", method = RequestMethod.GET)
    public Result getRouterNameList() {
        return service.getRouterName();
    }

    @ApiOperation(value = "权限组展示")
    @RequestMapping(value = "groups", method = RequestMethod.GET)
    public Result getAdminRightGroupList() {
        return adminRightGroupService.getAdminRightGroupList();
    }

    @ApiOperation(value = "获取权限")
    @RequestMapping(value = "list/{group_id}", method = RequestMethod.GET)
    public Result getrightList(@PathVariable("group_id") Integer group_id) {
        return adminRightGroupService.getRightList(group_id);
    }

    @ApiOperation(value = "获取指定值得的权限组信息")
    @RequestMapping(value = "get_group/{group_id}", method = RequestMethod.GET)
    public Result getGroup(@PathVariable("group_id") Integer group_id) {
        return Result.success(adminRightGroupService.getById(group_id));
    }

    @ApiOperation(value = "权限组修改")
    @RequestMapping(value = "update_group", method = RequestMethod.POST)
    public Result updateAdminRightGroup(@RequestBody AdminRightGroupUpdate dto) {
        return adminRightGroupService.updateAdminRightGroup(dto);
    }

    @ApiOperation(value = "权限组删除")
    @RequestMapping(value = "delete_group/{group_id}", method = RequestMethod.GET)
    public Result delAdminRightGroup(@PathVariable Integer group_id) {
        return adminRightGroupService.delAdminRightGroup(group_id);
    }

    @ApiOperation(value = "权限组添加")
    @RequestMapping(value = "add_group",method = RequestMethod.POST)
    public Result addAdminRightGroup(@RequestBody AdminRightGroupAdd dto) {
        return adminRightGroupService.addAdminRightGroup(dto);
    }

    @ApiOperation(value = "权限组禁用操作")
    @RequestMapping(value = "disabled_group/{group_id}",method = RequestMethod.POST)
    public Result updateIsdisabled(@PathVariable Integer group_id) {
        return adminRightGroupService.updateIsdisabled(group_id);
    }

    @ApiOperation(value = "获取指定权限")
    @RequestMapping(value = "get_right/{right_id}",method = RequestMethod.GET)
    public Result getright(@PathVariable Integer right_id) {
        return Result.success(service.getById(right_id));
    }

    @ApiOperation(value = "编辑权限")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Result update( @RequestBody AdminGroupRightUpdate dto) {
        return service.updateRight(dto);
    }

    @ApiOperation(value = "新增权限")
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Result addRight(@RequestBody AdminRightDevAdd dto) {
        return service.addRight(dto);
    }

    @ApiOperation(value = "删除权限")
    @RequestMapping(value = "delete/{right_id}",method = RequestMethod.POST)
    public Result delRight(@PathVariable Integer right_id) {
        return Result.success(service.removeById(right_id));
    }

    @ApiOperation(value = "子权限")
    @RequestMapping(value = "children/{right_id}",method = RequestMethod.GET)
    public Result children(@PathVariable Integer right_id) {
        return Result.success(service.children(right_id));
    }

}
