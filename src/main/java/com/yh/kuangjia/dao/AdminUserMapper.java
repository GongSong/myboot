package com.yh.kuangjia.dao;

import com.yh.kuangjia.entity.AdminUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yh.kuangjia.models.AdminUser.AdminUserRightList;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
public interface AdminUserMapper extends BaseMapper<AdminUser> {

    List<AdminUserRightList> GetSuperMenuRights();

    List<AdminUserRightList> GetAdminUserMenuRights(String roleids);
}
