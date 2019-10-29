package com.yh.kuangjia.models.SysBanner;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysBannerDevList implements Serializable {
    /**
     * 轮播图ID
     */
    @TableId(value = "banner_id", type = IdType.AUTO)
    private Integer banner_id;

    /**
     * 轮播类型
     */
    private Integer banner_type;

    private String banner_type_string;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;

    /**
     * 开始时间
     */
    private Integer begin_date;
    private String begin_date_string;

    /**
     * 结束时间
     */
    private Integer end_date;
    private String end_date_string;
}
