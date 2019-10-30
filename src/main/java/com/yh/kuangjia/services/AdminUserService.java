package com.yh.kuangjia.services;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.base.ResultList;
import com.yh.kuangjia.entity.AdminUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.kuangjia.models.AdminUser.AdminUserAdd;
import com.yh.kuangjia.models.AdminUser.AdminUserFilter;
import com.yh.kuangjia.models.AdminUser.AdminUserLogin;
import com.yh.kuangjia.models.AdminUser.AdminUserUpdate;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
public interface AdminUserService extends IService<AdminUser> {

    Result Login(AdminUserLogin dto);

    Result GetAdminUserMenuRightList(int adminid);

    Result getUserInfo(int adminid);

    ResultList getUserList(AdminUserFilter filter);

    Result addUser(Integer adminId, AdminUserAdd adminUserAdd);

    Result getInfo(int adminid);

    Result updateUser(Integer admin_id, AdminUserUpdate dto);

    Result updateIsDisabled(Integer admin_id,int adminid);

    Result Del(Integer admin_id,int adminid);

}
