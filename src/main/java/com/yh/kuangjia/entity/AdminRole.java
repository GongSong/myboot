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
@TableName("admin_role")
public class AdminRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer role_id;

    /**
     * 角色名称
     */
    private String role_name;

    /**
     * 添加时间
     */
    private Date create_time;

    /**
     * 添加人ID
     */
    private Integer admin_id;

    /**
     * 显示排序
     */
    private Integer sort;


}
