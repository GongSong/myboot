package com.yh.kuangjia.models.AdminUser;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yh.kuangjia.entity.SysRight;
import com.yh.kuangjia.models.SysRight.SysRightList;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class AdminUserConfig implements Serializable {

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

    private List<Map<String,Object>> menus;

    private List<Map<String,Object>> routers;

}
