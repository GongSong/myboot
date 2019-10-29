package com.yh.kuangjia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2019-10-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("admin_right")
public class AdminRight extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @TableId(value = "right_id", type = IdType.AUTO)
    private Integer right_id;

    /**
     * 权限组ID
     */
    private Integer group_id;

    /**
     * 权限名称
     */
    private String right_name;

    /**
     * 路由名称
     */
    private String router_name;

    /**
     * 组件路径
     */
    private String component_path;

    /**
     * 父级ID
     */
    private Integer parent_id;

    /**
     * 菜单组图标路径
     */
    private String icon;

    /**
     * 是否菜单权限
     */
    private Boolean is_menu;

    /**
     * 权限Url
     */
    private String url_path;

    /**
     * 控制器名称
     */
    private String controller;

    /**
     * 方法名称
     */
    private String action;

    /**
     * 请求方式
     */
    private Integer method;

    /**
     * 是否付费权限
     */
    private Boolean is_paid;

    /**
     * 显示排序
     */
    private Integer sort;

    /**
     * 权限说明
     */
    private String remark;


}
