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
@TableName("sys_right")
public class SysRight extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "right_id", type = IdType.AUTO)
    private Integer rightId;

    /**
     * 父id
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 菜单名称
     */
    @TableField("right_name")
    private String rightName;

    /**
     * 描述
     */
    private String description;

    /**
     * 菜单图标class
     */
    @TableField("menu_icon_class")
    private String menuIconClass;

    /**
     * 权限类型 0父菜单  1路由 2链接 3权限
     */
    @TableField("right_type")
    private Integer rightType;

    /**
     * 路由  链接 权限码
     */
    @TableField("right_value")
    private String rightValue;

    /**
     * 深度
     */
    private Integer deep;

    /**
     * 是否显示菜单
     */
    @TableField("is_menu")
    private Boolean isMenu;

    /**
     * 禁用
     */
    @TableField("is_disabled")
    private Boolean isDisabled;

    /**
     * 是否系统
     */
    @TableField("is_sys")
    private Boolean isSys;

    private Integer sort;

    @TableField("create_time")
    private Date createTime;


}
