package com.yh.kuangjia.services.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.entity.AdminRole;
import com.yh.kuangjia.dao.AdminRoleMapper;
import com.yh.kuangjia.models.AdminRole.AdminRoleUpdate;
import com.yh.kuangjia.services.AdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.kuangjia.util.Define.DefineUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

    @Autowired
    AdminRoleMapper mapper;

    @Override
    public Result getRoleList() {
        return Result.success(mapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Result delRole(int role_id) {
        if (mapper.deleteById(role_id)==0) return new Result(DefineUtil.DELETE_ERROR,DefineUtil.DELETE_ERROR_MSG);
        return Result.success();
    }

    @Override
    public Result updateRole(AdminRoleUpdate dto) {
        AdminRole adminRole = mapper.selectById(dto.getRole_id());
        adminRole.setRole_name(dto.getRole_name());
        adminRole.setSort(dto.getSort());
        if (mapper.updateById(adminRole)==0) return new Result(DefineUtil.UPDATE_ERROR,DefineUtil.UPDATE_ERROR_MSG);
        return Result.success();
    }
}
