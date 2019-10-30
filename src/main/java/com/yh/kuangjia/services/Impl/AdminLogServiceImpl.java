package com.yh.kuangjia.services.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yh.kuangjia.base.ResultList;
import com.yh.kuangjia.dao.AdminUserMapper;
import com.yh.kuangjia.entity.AdminLog;
import com.yh.kuangjia.dao.AdminLogMapper;
import com.yh.kuangjia.entity.AdminUser;
import com.yh.kuangjia.models.AdminLog.AdminLogFilter;
import com.yh.kuangjia.models.AdminLog.AdminLogList;
import com.yh.kuangjia.models.Enums.LogTypeEnum;
import com.yh.kuangjia.services.AdminLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.kuangjia.util.AdapterUtil;
import com.yh.kuangjia.util.DateUtil;
import com.yh.kuangjia.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AdminLogServiceImpl extends ServiceImpl<AdminLogMapper, AdminLog> implements AdminLogService {

    @Autowired
    AdminLogMapper mapper;
    @Autowired
    AdminUserMapper adminUserMapper;

    @Override
    public ResultList GetPage(AdminLogFilter filter) {
        List<AdminUser> adminUsers = adminUserMapper.selectList(null);
        adminUsers.stream().filter(f->null!=filter.getUser_name()&&!filter.getUser_name().equals("")&&f.getUser_name().equals(filter.getUser_name())).forEach(o->{
            filter.setAdmin_id(o.getAdmin_id());
        });
        QueryWrapper<AdminLog> pageFilter = new QueryWrapper<>();
        if (null!=filter.getLog_type()&&!filter.getLog_type().equals(0)) {
            pageFilter.eq("log_type",filter.getLog_type());
            if (null!=filter.getUser_name()&&!filter.getUser_name().equals("")) {
                pageFilter.eq("admin_id",filter.getAdmin_id());
            }
        } else {
            if (null!=filter.getUser_name()&&!filter.getUser_name().equals("")) {
                pageFilter.eq("admin_id",filter.getAdmin_id());
            }
        }
        if (null != filter.getBegin_date() && null != filter.getEnd_date() && !filter.getEnd_date().equals(0) && !filter.getBegin_date().equals(0)) {
            pageFilter.ge("create_date", filter.getBegin_date());
            pageFilter.le("create_date", filter.getEnd_date());
        }
        IPage<AdminLog> adminLogIPage = mapper.selectPage(new Page<>(filter.getPage_index(), filter.getPage_size()), pageFilter);
        List<AdminLogList> adapter = AdapterUtil.Adapter(adminLogIPage.getRecords(), AdminLogList.class);
        adapter.forEach(o->{
            adminUsers.stream().filter(f->f.getAdmin_id().equals(o.getAdmin_id())).forEach(o1->{
                o.setUser_name(o1.getUser_name());
                o.setLog_type_name(LogTypeEnum.valueOf(o.getLog_type()).getName());
            });
        });
        return ResultList.success(adapter,adminLogIPage.getTotal());
    }

    @Override
    public void addAdminLog(Integer adminID, LogTypeEnum _enum, int logKeyID, String remark) {
        AdminLog adapter = AdapterUtil.Adapter("", AdminLog.class);
        adapter.setAdmin_id(adminID);
        adapter.setLog_type(_enum.getCode());
        adapter.setLog_key_id(logKeyID);
        adapter.setRemark(remark);
        adapter.setAdmin_ip(IPUtil.getIpAddr());
        adapter.setCreate_date(DateUtil.GetDateInt());
        adapter.setCreate_time(DateUtil.GetDate());
        mapper.insert(adapter);
    }
}
