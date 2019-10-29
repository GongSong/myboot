package com.yh.kuangjia.models.AdminLog;


import com.yh.kuangjia.base.PageInfo;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdminLogFilter extends PageInfo implements Serializable {
    /**
     * 操作账号
     */
    private String user_name;

    /**
     * 操作账号ID
     */
    private Integer admin_id;

    /**
     * 日志类型
     */
    private Integer log_type;
}
