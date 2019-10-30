package com.yh.kuangjia.services;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.entity.AdminRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.kuangjia.models.AdminRole.AdminRoleUpdate;

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

    Result delRole(Integer admin_id,int role_id);

    Result updateRole(Integer admin_id,AdminRoleUpdate dto);

}
