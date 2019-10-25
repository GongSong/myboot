package com.yh.kuangjia.models.AdminUser;

import com.yh.kuangjia.core.annotation.ParamCheck;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdminUserLogin implements Serializable {

    @ParamCheck(notNull=true)
   /* @Check(notNull = true, maxLen = 30)
    @Check(notNull = true, maxLen = 3)
    @Check(maxLen = 320)*/

    private String user_name;
    @ParamCheck(notNull=true)
    private String user_pwd;
}
