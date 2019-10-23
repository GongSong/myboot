package com.yh.kuangjia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin")
public class SysAdmin extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    /**
     * 角色组
     */
    @TableField("role_ids")
    private String roleIds;

    /**
     * 账号
     */
    @TableField("user_name")
    private String userName;

    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 上次登录
     */
    @TableField("last_login_time")
    private Date lastLoginTime;

    @TableField("create_time")
    private Date createTime;

    /**
     * 锁定
     */
    @TableField("is_lock")
    private Boolean isLock;

    /**
     * 是否删除
     */
    @TableField("is_del")
    private Boolean isDel;


}
