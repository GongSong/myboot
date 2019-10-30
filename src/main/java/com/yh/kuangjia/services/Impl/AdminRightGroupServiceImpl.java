package com.yh.kuangjia.services.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.dao.AdminRightMapper;
import com.yh.kuangjia.entity.AdminRight;
import com.yh.kuangjia.entity.AdminRightGroup;
import com.yh.kuangjia.dao.AdminRightGroupMapper;
import com.yh.kuangjia.models.AdminRightGroup.AdminRightGroupAdd;
import com.yh.kuangjia.models.AdminRightGroup.AdminRightGroupUpdate;
import com.yh.kuangjia.services.AdminRightGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.kuangjia.util.SysDefine.DefineUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
@Service
public class AdminRightGroupServiceImpl extends ServiceImpl<AdminRightGroupMapper, AdminRightGroup> implements AdminRightGroupService {

    @Autowired
    AdminRightGroupMapper mapper;
    @Autowired
    AdminRightMapper adminRightMapper;

    @Override
    public Result getAdminRightGroupList() {
        List<AdminRightGroup> adminRightGroups = mapper.selectList(new QueryWrapper<>());
        return Result.success(adminRightGroups);
    }

    @Override
    public Result getRightList(Integer group_id) {
        List<AdminRight> adminRights = adminRightMapper.selectList(new QueryWrapper<AdminRight>().eq("group_id",group_id));
        return Result.success(adminRights);
    }

    @Override
    public Result updateAdminRightGroup(AdminRightGroupUpdate dto) {
        if (mapper.updateById(dto)==0) return new Result(DefineUtil.UPDATE_ERROR,DefineUtil.UPDATE_ERROR_MSG);
        return Result.success();
    }

    @Override
    @Transactional
    public Result delAdminRightGroup(Integer group_id) {
        adminRightMapper.delete(new QueryWrapper<AdminRight>().eq("group_id",group_id));
        mapper.deleteById(group_id);
        return Result.success();
    }

    @Override
    public Result addAdminRightGroup(AdminRightGroupAdd dto) {
       if ( mapper.insert(dto)==0) return new Result(DefineUtil.ADD_ERROR,DefineUtil.ADD_ERROR_MSG);
       return Result.success(dto);
    }

    @Override
    public Result updateIsdisabled(Integer group_id) {
        AdminRightGroup adminRightGroup = mapper.selectById(group_id);
        adminRightGroup.setIs_disabled(!adminRightGroup.getIs_disabled());
        if (mapper.updateById(adminRightGroup)==0) return new Result(DefineUtil.UPDATE_ERROR,DefineUtil.UPDATE_ERROR_MSG);
        return Result.success();
    }
}
