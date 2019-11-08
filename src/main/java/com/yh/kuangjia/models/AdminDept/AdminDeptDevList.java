package com.yh.kuangjia.models.AdminDept;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class AdminDeptDevList {

    /**
     * 部门ID
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer dept_id;

    /**
     * 父级ID
     */
    private Integer parent_dept_id;

    /**
     * 部门类型
     */
    private Integer dept_type;

    /**
     * 部门代号
     */
    private String dept_code;

    /**
     * 部门名称
     */
    private String dept_name;

    /**
     * 显示排序
     */
    private Integer sort;

    /**
     * 添加时间
     */
    private Date create_time;

    private Object children;
}
