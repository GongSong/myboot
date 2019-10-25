package com.yh.kuangjia.services;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.entity.AdminRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
public interface AdminRoleService extends IService<AdminRole> {

    Result getRoleList();

}
