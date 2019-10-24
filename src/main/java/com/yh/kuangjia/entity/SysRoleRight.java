package com.yh.kuangjia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2019-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role_right")
public class SysRoleRight extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rr_id", type = IdType.AUTO)
    private Integer rrId;

    @TableField("right_id")
    private Integer rightId;

    @TableField("role_id")
    private Integer roleId;


}
