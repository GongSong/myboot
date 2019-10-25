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
 * 管理用户部门
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("admin_dept")
public class AdminDept extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer dept_id;

    /**
     * 父级ID
     */
    private Integer parent_dept_id;

    /**
     * 部门类型
     */
    private Integer dept_type;

    /**
     * 部门代号
     */
    private String dept_code;

    /**
     * 部门名称
     */
    private String dept_name;

    /**
     * 显示排序
     */
    private Integer sort;

    /**
     * 添加时间
     */
    private Date create_time;


}
