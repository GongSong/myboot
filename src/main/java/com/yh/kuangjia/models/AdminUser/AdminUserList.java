package com.yh.kuangjia.models.AdminUser;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class AdminUserList implements Serializable {

    private Integer admin_id;

    private Date create_time;

    private Integer  dept_id;

    private String dept_name;

    private Boolean gender;

    private Boolean is_del;

    private Boolean is_disabled;

    private Boolean is_super;

    private String last_login_ip;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date last_login_time;

    private Integer login_times;

    private List<Integer> role_id_array;

    private String role_ids;

    private List<String> role_name_array;

    private String user_name;

    private String real_name;

    private String mobile;

    private String remark;

    private String email;


    /**
     * 是否部门主管
     */
    private Boolean is_dept_director;


}
