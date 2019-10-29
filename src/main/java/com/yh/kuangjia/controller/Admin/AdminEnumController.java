package com.yh.kuangjia.controller.Admin;


import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.core.BaseController;
import com.yh.kuangjia.models.SysDictionary.SysDictionaryTypesList;
import com.yh.kuangjia.util.EnumHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/enum")
@Api("系统类别相关接口")
public class AdminEnumController extends BaseController {

    @ApiOperation(value = "获取类别列表")
    @RequestMapping(value = "list/{type}", method = RequestMethod.GET)
    public Result GetTypes(@PathVariable("type") int type) {
        List<SysDictionaryTypesList> types = null;
        switch (type) {
            case 101:
                types = EnumHelper.GetDeptTypeEnumList();
                break;
            case 103:
                types = EnumHelper.GetLogTypeEnumList();
                break;
            case 104:
                types = EnumHelper.GetBannerTypeEnumList();
                break;
            case 105:
                types = Arrays.asList(
                        new SysDictionaryTypesList(1, "服务配置"),
                        new SysDictionaryTypesList(2, "设置配置"));
                break;
            case 106:
                types = Arrays.asList(
                        new SysDictionaryTypesList(1, "系统类别"),
                        new SysDictionaryTypesList(2, "银行")
                );
                break;
            default:
                break;
        }

        return Result.success(types);
    }
}
