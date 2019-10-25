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
 * @since 2019-10-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("admin_right_group")
public class AdminRightGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 组ID
     */
    @TableId(value = "group_id", type = IdType.AUTO)
    private Integer group_id;

    /**
     * 组名称
     */
    private String group_name;

    /**
     * 组路径
     */
    private String path;

    /**
     * 路由名称
     */
    private String router_name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否禁用
     */
    private Boolean is_disabled;

    /**
     * 显示排序
     */
    private Integer sort;


}
