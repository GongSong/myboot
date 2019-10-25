package com.yh.kuangjia.models.AdminDept;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AdminDeptViewList implements Serializable {
    /**
     * 部门ID
     */
    private Integer dept_id;

    /**
     * 部门名称
     */
    private String dept_name;

    /**
     *
     */
    private String parent_dept_name;

    /**
     *
     */
    private Integer dept_type;

    /**
     * 显示排序
     */
    private Integer sort;

    private String dept_type_name;

    private List<String> dept_director_array;

}
