package com.yh.kuangjia.services.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.dao.AdminUserMapper;
import com.yh.kuangjia.entity.AdminDept;
import com.yh.kuangjia.dao.AdminDeptMapper;
import com.yh.kuangjia.entity.AdminUser;
import com.yh.kuangjia.models.AdminDept.*;
import com.yh.kuangjia.models.Enums.AdminDeptTypeEnum;
import com.yh.kuangjia.models.Enums.LogTypeEnum;
import com.yh.kuangjia.models.SingleID;
import com.yh.kuangjia.services.AdminDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.kuangjia.services.AdminLogService;
import com.yh.kuangjia.util.AdapterUtil;
import com.yh.kuangjia.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 管理用户部门 服务实现类
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
@Service
public class AdminDeptServiceImpl extends ServiceImpl<AdminDeptMapper, AdminDept> implements AdminDeptService {

    @Autowired
    AdminDeptMapper mapper;
    @Autowired
    AdminUserMapper adminUserMapper;
    @Autowired
    AdminLogService adminLogService;

    @Override
    public Result Devlist() {
        return Result.success(mapper.selectList(new QueryWrapper<AdminDept>()));
    }

    @Override
    public Result tree() {
        List<AdminDept> adminDepts = mapper.selectList(new QueryWrapper<AdminDept>());
        AdminDept adminDept = adminDepts.parallelStream().filter(f -> f.getParent_dept_id().equals(0)).findFirst().orElse(null);
        AdminDeptContext adminDeptContext = new AdminDeptContext();
        if (null != adminDept) {
            adminDeptContext.setId(adminDept.getDept_id());
            adminDeptContext.setLabel(adminDept.getDept_name());
        }
        List<AdminDeptContext> children = children(adminDeptContext.getId());
        children.forEach(o -> {
            List<AdminDeptContext> children1 = children(o.getId());
            o.setChildren(children1);
            if (children1.size() != 0) {
                children1.forEach(o1 -> {
                    List<AdminDeptContext> children2 = children(o1.getId());
                    o1.setChildren(children2);
                });
            }
        });
        adminDeptContext.setChildren(children);
        return Result.success(adminDeptContext);
    }

    @Override
    public Result tree2() {
        List<AdminDept> adminDepts = mapper.selectList(new QueryWrapper<AdminDept>());
        AdminDept adminDept = adminDepts.parallelStream().filter(f -> f.getParent_dept_id().equals(0)).findFirst().orElse(null);
        List<AdminDept> collect = adminDepts.parallelStream().filter(f -> !f.getParent_dept_id().equals(0)).collect(Collectors.toList());
        AdminDeptDevList adapter = AdapterUtil.Adapter(adminDept, AdminDeptDevList.class);
        Map<Integer, List<AdminDept>> collect1 = collect.stream().collect(Collectors.groupingBy(AdminDept::getParent_dept_id));
        List<AdminDeptDevList> adapter1 = AdapterUtil.Adapter(collect, AdminDeptDevList.class);
        adapter1.sort((h1,h2)->h2.getDept_id().compareTo(h1.getDept_id()));

        adapter.setChildren(adapter1);
        return Result.success(adapter);
    }




    @Override
    @Transactional
    public Result Delete(int adminID, SingleID dto) {
        AdminDept entity = mapper.selectById(dto.getSingle_id());
        if (null == entity) {
            return new Result(Define.INPUT_ERROR, Define.INPUT_ERROR_MSG);
        }
        mapper.delete(new QueryWrapper<AdminDept>().eq("parent_dept_id", dto.getSingle_id()));
        if (mapper.deleteById(entity) == 0) {
            return new Result(Define.DELETE_ERROR, Define.DELETE_ERROR_MSG);
        }
        //未添加操作记录
        adminLogService.addAdminLog(adminID, LogTypeEnum.Dept, adminID, "删除部门：" + entity.getDept_name());
        return Result.success();
    }

    @Override
    public Result view(int dept_id) {
        AdminDept adminDept = mapper.selectById(dept_id);
        AdminDeptViewList adapter = AdapterUtil.Adapter(adminDept, AdminDeptViewList.class);
        adapter.setDept_director_array(new ArrayList<>());
        adapter.setDept_type(adminDept.getDept_type().intValue());
        AdminDept adminDept1 = mapper.selectById(adminDept.getParent_dept_id());
        if (null != adminDept1) {
            adapter.setParent_dept_name(adminDept1.getDept_name());
        }
        adapter.setDept_type_name(AdminDeptTypeEnum.valueOf(adminDept.getDept_type().intValue()).getName());
        List<AdminUser> adminUsers = adminUserMapper.selectList(new QueryWrapper<AdminUser>().eq("dept_id", dept_id).eq("is_dept_director", true));
        adminUsers.forEach(adminUser -> {
            adapter.getDept_director_array().add(adminUser.getUser_name());
        });
        return Result.success(adapter);
    }

    @Override
    public Result Add(int adminID, AdminDeptAdd dto) {
        AdminDept adapter = AdapterUtil.Adapter(dto, AdminDept.class);
        adapter.setCreate_time(DateUtil.GetDate());
        adapter.setParent_dept_id(dto.getParent_dept_id());
        AdminDept adminDept = mapper.selectOne(new QueryWrapper<AdminDept>().eq("parent_dept_id", dto.getParent_dept_id()));
        if (null == adminDept) {
            AdminDept adminDept1 = mapper.selectById(dto.getParent_dept_id());
            adapter.setDept_code(adminDept1.getDept_code() + "100");
        } else {
            int i = Integer.parseInt(adminDept.getDept_code().substring(adminDept.getDept_code().length() - 1));
            ++i;
            String s = String.valueOf(i);
            adapter.setDept_code(adminDept.getDept_code().substring(0, adminDept.getDept_code().length() - 1) + s);
        }
        if (mapper.insert(adapter) == 0) {
            return new Result(Define.ADD_ERROR, Define.ADD_ERROR_MSG);
        }
        adminLogService.addAdminLog(adminID, LogTypeEnum.Dept, adminID, "新增部门：" + adapter.getDept_name());
        AdminDeptContext adminDeptContext = new AdminDeptContext();
        adminDeptContext.setId(adapter.getDept_id());
        adminDeptContext.setLabel(dto.getDept_name());
        return Result.success(adminDeptContext);
    }

    @Override
    public Result getInfo(int dept_id) {
        AdminDept adminDept = mapper.selectById(dept_id);
        AdminDeptList adapter = AdapterUtil.Adapter(adminDept, AdminDeptList.class);
        adapter.setDept_type(adminDept.getDept_type().intValue());
        if (!adminDept.getParent_dept_id().equals(0)) {
            adapter.setParent_dept_name(mapper.selectOne(new QueryWrapper<AdminDept>().eq("dept_id", adminDept.getParent_dept_id())).getDept_name());
        }
        return Result.success(adapter);
    }

    @Override
    public Result Update(int adminID, AdminDeptEdit dto) {
        AdminDept entity = mapper.selectById(dto.getDept_id());
        if (entity == null) {
            return new Result(Define.INPUT_ERROR, Define.INPUT_ERROR_MSG);
        }
        entity.setDept_name(dto.getDept_name());
        entity.setSort(dto.getSort());
        entity.setCreate_time(DateUtil.GetDate());
        if (mapper.updateById(entity) == 0) {
            return new Result(Define.UPDATE_ERROR, Define.UPDATE_ERROR_MSG);
        }
        adminLogService.addAdminLog(adminID, LogTypeEnum.Dept, adminID, "编辑部门：" + entity.getDept_name());
        AdminDeptContext adminDeptContext = new AdminDeptContext();
        adminDeptContext.setId(dto.getDept_id());
        adminDeptContext.setLabel(dto.getDept_name());
        return Result.success(adminDeptContext);
    }

    //共用查询子部门
    public List<AdminDeptContext> children(Integer id) {
        List<AdminDept> adminDepts = mapper.selectList(new QueryWrapper<AdminDept>().eq("parent_dept_id", id));
        List<AdminDeptContext> adminDeptContexts = new ArrayList<>();
        if (adminDepts.size() != 0) {
            adminDepts.forEach(adminDept -> {
                AdminDeptContext adminDeptContext1 = new AdminDeptContext();
                adminDeptContext1.setLabel(adminDept.getDept_name());
                adminDeptContext1.setId(adminDept.getDept_id());
                adminDeptContexts.add(adminDeptContext1);
            });
        }
        return adminDeptContexts;
    }

    /**
     * 业务错误代码定义
     */
    private static class Define {
        public static Integer INPUT_ERROR = 1;
        public static String INPUT_ERROR_MSG = "信息不存在";

        public static Integer DELETE_ERROR = 1;
        public static String DELETE_ERROR_MSG = "删除错误";

        public static Integer UPDATE_ERROR = 1;
        public static String UPDATE_ERROR_MSG = "更新错误";

        public static Integer EXIST_ERROR = 1;
        public static String EXIST_ERROR_MSG = "代码已存在";

        public static Integer ADD_ERROR = 1;
        public static String ADD_ERROR_MSG = "插入错误";
    }
}
