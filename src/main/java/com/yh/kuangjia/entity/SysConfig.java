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
 * 平台配置
 * </p>
 *
 * @author 任性
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_config")
public class SysConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 配置ID
     */
    @TableId(value = "config_id", type = IdType.AUTO)
    private Integer config_id;

    /**
     * 配置类型（枚举）
     */
    private Integer type;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置代号
     */
    private String code;

    /**
     * 配置值
     */
    private String value;

    /**
     * 配置说明
     */
    private String remark;


}
