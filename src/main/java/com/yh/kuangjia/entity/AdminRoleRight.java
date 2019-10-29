package com.yh.kuangjia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("admin_role_right")
public class AdminRoleRight extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer role_id;

    private Integer right_id;


}
