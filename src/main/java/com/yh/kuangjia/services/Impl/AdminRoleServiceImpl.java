package com.yh.kuangjia.services.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.entity.AdminRole;
import com.yh.kuangjia.dao.AdminRoleMapper;
import com.yh.kuangjia.services.AdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
