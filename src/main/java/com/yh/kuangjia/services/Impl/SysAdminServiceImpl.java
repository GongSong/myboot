package com.yh.kuangjia.services.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.base.TokenAdmin;
import com.yh.kuangjia.base.TokenHelper;
import com.yh.kuangjia.dao.SysRightMapper;
import com.yh.kuangjia.entity.SysAdmin;
import com.yh.kuangjia.dao.SysAdminMapper;
import com.yh.kuangjia.entity.SysRight;
import com.yh.kuangjia.models.AdminUser.AdminUserConfig;
import com.yh.kuangjia.models.AdminUser.AdminUserLogin;
import com.yh.kuangjia.services.ISysAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.kuangjia.util.AdapterUtil;
import com.yh.kuangjia.util.DateUtil;
import com.yh.kuangjia.util.security.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 任性
 * @since 2019-10-23
 */
@Service("SysAdminService")
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements ISysAdminService {

    @Autowired
    SysAdminMapper mapper;
    @Autowired
    SysRightMapper sysRightMapper;

    @Override
    public Result login(AdminUserLogin dto) {
        SysAdmin sysAdmin = mapper.selectOne(new QueryWrapper<SysAdmin>().eq("user_name", dto.getUsername()));
        if (null == sysAdmin) {
            return new Result(Define.INPUT_ERROR, Define.INPUT_ERROR_MSG);
        }
        if (sysAdmin.getIsLock().equals(true)) {
            return new Result(Define.DISABLED_ERROR, Define.DISABLED_ERROR_MSG);
        }
        if (!sysAdmin.getPassword().equals(MD5Util.ToMD5(dto.getPassword()))) {
            return new Result(Define.PWD_ERROR, Define.PWD_ERROR_MSG);
        }
        sysAdmin.setLastLoginTime(DateUtil.GetDate());
        mapper.updateById(sysAdmin);
        TokenAdmin token = new TokenAdmin();
        token.setAdminId(sysAdmin.getAdminId());
        token.setRoleIDs(sysAdmin.getRoleIds());
        return Result.success(TokenHelper.GetAccessTokenAdmin(token));
    }

    @Override
    public Result config(Integer admin_id) {
        List<SysRight> deep = sysRightMapper.selectList(new QueryWrapper<SysRight>().le("deep",1).eq("is_disabled",false).eq("is_menu",true));
        SysAdmin sysAdmin = mapper.selectById(admin_id);
        AdminUserConfig adapter = AdapterUtil.Adapter(sysAdmin, AdminUserConfig.class);

        return Result.success(adapter);
    }


    /**
     * 业务错误代码定义
     */
    private static class Define {
        public static Integer INPUT_ERROR = 1;
        public static String INPUT_ERROR_MSG = "用户名不存在";

        public static Integer DISABLED_ERROR = 1;
        public static String DISABLED_ERROR_MSG = "用户已被禁用，无法登录";

        public static Integer PWD_ERROR = 1;
        public static String PWD_ERROR_MSG = "密码输入错误";

        public static Integer ROLEIDS_ERROR = 1;
        public static String ROLEIDS_ERROR_MSG = "用户角色为空";
    }
}
