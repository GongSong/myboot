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
 * 数据字典
 * </p>
 *
 * @author 任性
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dictionary")
public class SysDictionary extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Integer dict_id;

    /**
     * 字典类型（枚举）
     */
    private Integer dict_type;

    /**
     * 字典名称
     */
    private String dict_name;

    /**
     * 字典代号
     */
    private String dict_code;

    /**
     * 字典值
     */
    private String dict_value;

    /**
     * 是否禁用
     */
    private Boolean is_disabled;

    /**
     * 排序
     */
    private Integer sort;


}
