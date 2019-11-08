package com.yh.kuangjia.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yh.kuangjia.KuangjiaApplication;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.dao.AdminDeptMapper;
import com.yh.kuangjia.entity.AdminDept;
import com.yh.kuangjia.models.AdminDept.AdminDeptContext;
import com.yh.kuangjia.util.ALiYun.ALiYunConfig;
import com.yh.kuangjia.util.QRCodeUtil.QrUtil.PutZipUtil;
import com.yh.kuangjia.util.ResourceUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KuangjiaApplication.class)
public class Demo {

    @Autowired
    AdminDeptMapper mapper;

    @Test
    public void test() {
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
        System.out.println(adminDeptContext);
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

}
