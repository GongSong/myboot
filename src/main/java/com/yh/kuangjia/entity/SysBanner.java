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
 * @since 2019-10-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_banner")
public class SysBanner extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 轮播图ID
     */
    @TableId(value = "banner_id", type = IdType.AUTO)
    private Integer banner_id;

    /**
     * 轮播类型
     */
    private Integer banner_type;

    /**
     * 轮播图名称
     */
    private String title;

    /**
     * 轮播图片
     */
    private String pic;

    /**
     * 跳转链接
     */
    private String url;

    /**
     * 显示排序
     */
    private Integer sort;

    /**
     * 是否显示
     */
    private Boolean is_show;

    /**
     * 添加时间
     */
    private Date create_time;

    /**
     * 开始时间
     */
    private Integer begin_date;

    /**
     * 结束时间
     */
    private Integer end_date;


}
