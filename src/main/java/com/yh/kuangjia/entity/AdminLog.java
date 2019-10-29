package com.yh.kuangjia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yh.kuangjia.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 任性
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("admin_log")
public class AdminLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer log_id;

    /**
     * 用户ID
     */
    private Integer admin_id;

    /**
     * 日志类型
     */
    private Integer log_type;

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
    private Date create_time;

    /**
     * 日志内容
     */
    private String remark;


}
