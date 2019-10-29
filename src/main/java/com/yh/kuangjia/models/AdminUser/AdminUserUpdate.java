package com.yh.kuangjia.models.AdminUser;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class AdminUserUpdate implements Serializable {

    private Integer admin_id;

    private Date create_time;

    private Integer dept_id;

    private String dept_name;

    private String email;

    private Boolean gender;

    private Boolean is_del;

    private Boolean is_disabled;

    private Boolean is_status_ok;

    private Boolean is_super;

    private String last_login_ip;

    private String last_login_time;

    private Integer login_times;

    private String mobile;

    private String real_name;

    private String remark;

    private List<Integer> role_id_array;

    private String role_ids;

    private List<String> role_name_array;

    private Integer sex;

    private String user_name;

    private String user_pwd;

    private String pwd_salt;

    /**
     * 是否部门主管
     */
    private Boolean is_dept_director;


}
