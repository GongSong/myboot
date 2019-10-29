package com.yh.kuangjia.services;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.entity.AdminRight;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.kuangjia.models.AdminRight.AdminRightAdd;
import com.yh.kuangjia.models.AdminRight.AdminRightDevAdd;
import com.yh.kuangjia.models.AdminRightGroup.AdminGroupRightUpdate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 任性
 * @since 2019-10-28
 */
public interface AdminRightService extends IService<AdminRight> {

    Result getRight(Integer role_id);

    Result getRouterName();

    Result setRight(AdminRightAdd dto);

    Result updateRight(AdminGroupRightUpdate dto);

    Result addRight(AdminRightDevAdd dto);

    Result children( Integer right_id);


}
