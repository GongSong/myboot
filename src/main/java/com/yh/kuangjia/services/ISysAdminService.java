package com.yh.kuangjia.services;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.entity.SysAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.kuangjia.models.AdminUser.AdminUserLogin;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 任性
 * @since 2019-10-23
 */
public interface ISysAdminService extends IService<SysAdmin> {

    Result login(AdminUserLogin dto);

    Result config(Integer admin_id);

}
