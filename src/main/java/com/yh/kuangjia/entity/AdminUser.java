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
 * @since 2019-10-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("admin_user")
public class AdminUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer admin_id;

    /**
     * 用户名
     */
    private String user_name;

    /**
     * 密码盐
     */
    private String pwd_salt;

    /**
     * 登录密码
     */
    private String user_pwd;

    /**
     * 角色ID
     */
    private String role_ids;

    /**
     * 部门ID
     */
    private Integer dept_id;

    /**
     * 部门代号
     */
    private String dept_code;

    /**
     * 姓名
     */
    private String real_name;

    /**
     * 性别
     */
    private Boolean gender;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String profile_photo;

    /**
     * 是否部门主管
     */
    private Boolean is_dept_director;

    /**
     * 是否超级管理员
     */
    private Boolean is_super;

    /**
     * 是否禁用
     */
    private Boolean is_disabled;

    /**
     * 是否删除
     */
    private Boolean is_del;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 创建日期
     */
    private Integer create_date;

    /**
     * 登录次数
     */
    private Integer login_times;

    /**
     * 最后登录时间
     */
    private Date last_login_time;

    /**
     * 最后登录日期
     */
    private Integer last_login_date;

    /**
     * 最后登录IP
     */
    private String last_login_ip;

    /**
     * 备注
     */
    private String remark;


}
