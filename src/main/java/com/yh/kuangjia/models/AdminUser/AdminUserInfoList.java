package com.yh.kuangjia.models.AdminUser;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class AdminUserInfoList implements Serializable {

    private String avatar;

    private String dept_name;

    private Boolean gender;

    private Date login_time;

    private String real_name;

    private List<String> role_names;

    private String user_name;

    private String role_ids;

}
