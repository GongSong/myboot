package com.yh.kuangjia.services;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.entity.AdminRightGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.kuangjia.models.AdminRightGroup.AdminRightGroupAdd;
import com.yh.kuangjia.models.AdminRightGroup.AdminRightGroupUpdate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
public interface AdminRightGroupService extends IService<AdminRightGroup> {

    Result getAdminRightGroupList();

    Result getRightList(Integer group_id);

    Result updateAdminRightGroup(AdminRightGroupUpdate dto);

    Result delAdminRightGroup(Integer group_id);

    Result addAdminRightGroup(AdminRightGroupAdd dto);

    Result updateIsdisabled(Integer group_id);

}
