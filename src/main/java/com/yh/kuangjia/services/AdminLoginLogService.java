package com.yh.kuangjia.services;

import com.yh.kuangjia.base.ResultList;
import com.yh.kuangjia.entity.AdminLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.kuangjia.models.AdminLoginLog.AdminLoginLogFilter;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 任性
 * @since 2019-10-29
 */
public interface AdminLoginLogService extends IService<AdminLoginLog> {

    void addLoginLog(String user_name,Boolean is_succeed);

    ResultList GetPage(AdminLoginLogFilter filter);

}
