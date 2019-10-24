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
 * @since 2019-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_banner")
public class sysBanner extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "banner_id", type = IdType.AUTO)
    private Integer banner_id;

    /**
     * 轮播代号
     */
    private String code;

    private String url;

    private String title;

    private String target;

    private String remark;

    /**
     * 永久有效
     */
    private Boolean is_forever;

    /**
     * 展示时间
     */
    private Date display_time;

    /**
     * 过期时间
     */
    private Date expired_time;

    private Integer sort;


}
