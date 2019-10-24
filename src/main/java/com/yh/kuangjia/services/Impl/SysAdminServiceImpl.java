package com.yh.kuangjia.services.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.base.TokenAdmin;
import com.yh.kuangjia.base.TokenHelper;
import com.yh.kuangjia.dao.SysRightMapper;
import com.yh.kuangjia.dao.SysRoleRightMapper;
import com.yh.kuangjia.entity.SysAdmin;
import com.yh.kuangjia.dao.SysAdminMapper;
import com.yh.kuangjia.entity.SysRight;
import com.yh.kuangjia.entity.SysRoleRight;
import com.yh.kuangjia.models.AdminUser.AdminUserConfig;
import com.yh.kuangjia.models.AdminUser.AdminUserLogin;
import com.yh.kuangjia.services.ISysAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.kuangjia.util.AdapterUtil;
import com.yh.kuangjia.util.DateUtil;
import com.yh.kuangjia.util.security.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    SysRoleRightMapper sysRoleRightMapper;

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
        SysAdmin sysAdmin = mapper.selectById(admin_id);
        List<Map<String, Object>> menus = new ArrayList<>();
        List<Map<String, Object>> routers = new ArrayList<>();
        List<String> rolelists = Arrays.asList(sysAdmin.getRoleIds().split(","));
        List<SysRoleRight> role_id = sysRoleRightMapper.selectList(new QueryWrapper<SysRoleRight>().in("role_id", rolelists));
        List<SysRight> deep = sysRightMapper.selectList(new QueryWrapper<SysRight>().le("deep", 1).eq("is_disabled", false).eq("is_menu", true));
        AdminUserConfig adapter = AdapterUtil.Adapter(sysAdmin, AdminUserConfig.class);
        List<SysRight> collect = deep.parallelStream().filter(f -> f.getParentId().equals(0) && f.getDeep().equals(0) && f.getRightType().compareTo(1) <= 0).collect(Collectors.toList());
        collect.sort((h1, h2) -> h2.getSort().compareTo(h1.getSort()));
        collect.forEach(o -> {
            Map<String, Object> map = Maps.newHashMap();
            map.put("icon", o.getMenuIconClass());
            map.put("index", o.getRightId());
            map.put("path", o.getRightValue());
            map.put("title", o.getRightName());
            List<Map<String, Object>> list2 = new ArrayList<>();
            List<SysRight> collect1 = deep.parallelStream().filter(f -> f.getParentId().equals(o.getRightId())).collect(Collectors.toList());
            if (null != collect1 && collect1.size() != 0) {
                collect1.sort((h1, h2) -> h2.getSort().compareTo(h1.getSort()));
                collect1.forEach(o1 -> {
                    Map<String, Object> map1 = Maps.newHashMap();
                    map1.put("icon", o1.getMenuIconClass());
                    map1.put("index", o1.getRightId());
                    map1.put("path", o1.getRightValue());
                    map1.put("title", o1.getRightName());
                    list2.add(map1);
                });
            }
            if (null != list2 && list2.size() != 0) {
                map.put("subs", list2);
            }
            Map<String, Object> router = Maps.newHashMap();
            router.put("title", o.getRightName());
            router.put("route", o.getRightValue());
            router.put("path", o.getRightValue().toLowerCase());
            router.put("rights", new ArrayList<>());
            menus.add(map);
            routers.add(router);
        });
        adapter.setMenus(menus);
        adapter.setRouters(routers);
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
