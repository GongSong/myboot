package com.yh.kuangjia.models.AdminLog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdminLogList implements Serializable {
    /**
     * 日志ID
     */
    private Integer log_id;

    /**
     * 用户ID
     */
    private Integer admin_id;
    private String user_name;
    /**
     * 日志类型
     */
    private Integer log_type;
    private String log_type_name;

    /**
     * 日志类型对应的主键ID
     */
    private Integer log_key_id;

    /**
     * 操作IP
     */
    private String admin_ip;

    /**
     * 操作日期
     */
    private Integer create_date;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date create_time;

    /**
     * 日志内容
     */
    private String remark;

}