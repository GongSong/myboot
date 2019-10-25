package com.yh.kuangjia.services;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.entity.AdminDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.kuangjia.models.AdminDept.AdminDeptAdd;
import com.yh.kuangjia.models.SingleID;

/**
 * <p>
 * 管理用户部门 服务类
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
public interface AdminDeptService extends IService<AdminDept> {

    Result Devlist();

    Result tree();

    Result Delete(int adminID, SingleID dto);

    Result view(int  dept_id);

    Result Add(int adminID, AdminDeptAdd dto);
}
