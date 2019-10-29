package com.yh.kuangjia.models.AdminRole;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminRoleUpdate implements Serializable {

    private Integer role_id;

    private String role_name;

    private Integer sort;

    private Integer admin_id;

}
