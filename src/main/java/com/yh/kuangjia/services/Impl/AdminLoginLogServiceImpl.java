package com.yh.kuangjia.services.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yh.kuangjia.base.ResultList;
import com.yh.kuangjia.entity.AdminLoginLog;
import com.yh.kuangjia.dao.AdminLoginLogMapper;
import com.yh.kuangjia.models.AdminLoginLog.AdminLoginLogFilter;
import com.yh.kuangjia.services.AdminLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.kuangjia.util.AdapterUtil;
import com.yh.kuangjia.util.DateUtil;
import com.yh.kuangjia.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 任性
 * @since 2019-10-29
 */
@Service
public class AdminLoginLogServiceImpl extends ServiceImpl<AdminLoginLogMapper, AdminLoginLog> implements AdminLoginLogService {

    @Autowired
    AdminLoginLogMapper mapper;

    @Override
    public void addLoginLog(String user_name,Boolean is_succeed) {
        AdminLoginLog adapter = AdapterUtil.Adapter("", AdminLoginLog.class);
        adapter.setLogin_time(DateUtil.GetDate());
        adapter.setLogin_date(DateUtil.GetDateInt());
        adapter.setIs_succeed(is_succeed);
        adapter.setLogin_ip(IPUtil.getIpAddr());
        adapter.setUser_name(user_name);
        mapper.insert(adapter);
    }

    @Override
    public ResultList GetPage(AdminLoginLogFilter filter) {
        QueryWrapper<AdminLoginLog> pagefilter = new QueryWrapper<>();
        if (null!=filter.getIs_succeed()&&filter.getIs_succeed().equals(1) && null != filter.getUser_name() && !filter.getUser_name().equals("")) {
            pagefilter.eq("is_succeed", true);
            pagefilter.like("user_name", filter.getUser_name());
        }
        if (null!=filter.getIs_succeed()&&filter.getIs_succeed().equals(2) && null != filter.getUser_name() && !filter.getUser_name().equals("")) {
            pagefilter.eq("is_succeed", false);
            pagefilter.like("user_name", filter.getUser_name());
        }
        if ( null != filter.getBegin_date() && null != filter.getEnd_date() && !filter.getEnd_date().equals(0) && !filter.getBegin_date().equals(0)) {
            pagefilter.ge("login_date", filter.getBegin_date());
            pagefilter.le("login_date", filter.getEnd_date());
        }
        pagefilter.orderByDesc("login_id");
        IPage<AdminLoginLog> adminLoginLogIPage = mapper.selectPage(new Page<>(filter.getPage_index(), filter.getPage_size()), pagefilter);
        return ResultList.success(adminLoginLogIPage.getRecords(),adminLoginLogIPage.getTotal());
    }
}
