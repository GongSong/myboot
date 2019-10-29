package com.yh.kuangjia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("admin_login_log")
public class AdminLoginLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 登录ID
     */
    @TableId(value = "login_id", type = IdType.AUTO)
    private Integer login_id;

    /**
     * 用户名
     */
    private String user_name;

    /**
     * 是否成功
     */
    private Boolean is_succeed;

    /**
     * 登录IP
     */
    private String login_ip;

    /**
     * 登录日期
     */
    private Integer login_date;

    /**
     * 登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date login_time;


}
