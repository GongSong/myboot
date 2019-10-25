package com.yh.kuangjia.models.AdminUser;


import com.yh.kuangjia.entity.AdminUser;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdminUserAdd extends AdminUser implements Serializable {

    private Boolean is_status_ok;

    private int[] role_id_array;

    private int[] user_type_array;

}
