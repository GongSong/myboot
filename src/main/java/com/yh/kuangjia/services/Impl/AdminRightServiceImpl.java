package com.yh.kuangjia.services.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.dao.AdminRightGroupMapper;
import com.yh.kuangjia.dao.AdminRoleRightMapper;
import com.yh.kuangjia.entity.AdminRight;
import com.yh.kuangjia.dao.AdminRightMapper;
import com.yh.kuangjia.entity.AdminRoleRight;
import com.yh.kuangjia.models.AdminRight.*;
import com.yh.kuangjia.models.AdminRightGroup.AdminGroupRightUpdate;
import com.yh.kuangjia.services.AdminRightService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.kuangjia.util.AdapterUtil;
import com.yh.kuangjia.util.SysDefine.DefineUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 任性
 * @since 2019-10-28
 */
@Service
public class AdminRightServiceImpl extends ServiceImpl<AdminRightMapper, AdminRight> implements AdminRightService {

    @Autowired
    AdminRoleRightMapper adminRoleRightMapper;
    @Autowired
    AdminRightGroupMapper adminRightGroupMapper;
    @Autowired
    AdminRightMapper adminRightMapper;

    @Override
    public Result getRight(Integer role_id) {
        Set<Integer> set = new HashSet<>();
        List<AdminRoleRightList> lists = AdapterUtil.Adapter(adminRoleRightMapper.selectList(new QueryWrapper<AdminRoleRight>().eq("role_id", role_id)), AdminRoleRightList.class);
        lists.forEach(adminRoleRightList -> set.add(adminRoleRightList.getRight_id()));
        List<AdminRightGroupList> adminRightGroupLists = adminRightGroupMapper.selectGroupNameAndGroupid();
        List<AdminRightList> adminRightLists = adminRightMapper.selectInfo();
        List<AdminRightGroupList> rightGroupList = new ArrayList<>();
        adminRightGroupLists.forEach(adminRightGroupList -> {
            AdminRightGroupList adminRightGroupList1 = new AdminRightGroupList();
            List<AdminRightList> rightList = new ArrayList<AdminRightList>();
            adminRightLists.forEach(adminRightList -> {
                if (adminRightList.getGroup_id() == adminRightGroupList.getGroup_id()) {
                    if (set.add(adminRightList.getRight_id())) {
                        adminRightList.setChecked(false);
                    } else {
                        adminRightList.setChecked(true);
                    }
                    adminRightList.setTitle(adminRightList.getTitle());
                    adminRightList.setRight_id(adminRightList.getRight_id());
                    rightList.add(adminRightList);
                    adminRightGroupList1.setGroup_id(adminRightList.getGroup_id());
                    adminRightGroupList1.setTitle(adminRightGroupList.getTitle());
                }
            });
            adminRightGroupList1.setChildren(rightList);
            rightGroupList.add(adminRightGroupList1);
        });
        return Result.success(rightGroupList);
    }

    @Override
    public Result getRouterName() {
        List<String> collect = adminRightMapper.selectList(new QueryWrapper<AdminRight>()).stream().map(AdminRight::getRouter_name).collect(Collectors.toList());
        return Result.success(collect);
    }

    @Override
    public Result setRight(AdminRightAdd dto) {
        adminRoleRightMapper.delete(new QueryWrapper<AdminRoleRight>().eq("role_id", dto.getRole_id()));
        if (null == dto.getRight_id_array() || dto.getRight_id_array().size() == 0) return Result.success();
        dto.getRight_id_array().forEach(o -> {
            AdminRoleRight adapter = AdapterUtil.Adapter("", AdminRoleRight.class);
            adapter.setRole_id(dto.getRole_id());
            adapter.setRight_id(o);
            adminRoleRightMapper.insert(adapter);
        });
        return Result.success("保存成功");
    }

    @Override
    public Result updateRight(AdminGroupRightUpdate dto) {
        if (adminRightMapper.updateById(dto) == 0)
            return new Result(DefineUtil.UPDATE_ERROR, DefineUtil.UPDATE_ERROR_MSG);
        return Result.success();
    }

    @Override
    public Result addRight(AdminRightDevAdd dto) {
        if (null == dto.getIs_paid()) {
            dto.setIs_paid(false);
        }
        dto.setParent_id(dto.getGroup_id());
        if (adminRightMapper.insert(dto)==0) return new Result(DefineUtil.ADD_ERROR,DefineUtil.ADD_ERROR_MSG);
        return Result.success(dto);
    }

    @Override
    public Result children(Integer right_id) {
        return Result.success(adminRightMapper.selectList(new QueryWrapper<AdminRight>().eq("parent_id",right_id)));
    }
}
