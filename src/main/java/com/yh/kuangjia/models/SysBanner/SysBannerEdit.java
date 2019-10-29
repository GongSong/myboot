package com.yh.kuangjia.models.SysBanner;


import com.yh.kuangjia.util.DateUtil;

import java.io.Serializable;

public class SysBannerEdit implements Serializable {
    /**
     * 轮播图id
     */
    private Integer banner_id;

    /**
     * 轮播图名称
     */
    private String title;
    /**
     * 轮播类型
     */
    private Integer banner_type;
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
     * 开始时间
     */
    private String begin_date_string;
    private Integer begin_date;
    /**
     * 结束时间
     */
    private String end_date_string;
    private Integer end_date;

    public String getBegin_date_string() {
        if (begin_date == null) return "";
        return DateUtil.GetIntToDateString(begin_date);
    }

    public void setBegin_date_string(String begin_date_string) {
        this.begin_date_string = begin_date_string;
    }

    public String getEnd_date_string() {
        if (end_date == null) return "";
        return DateUtil.GetIntToDateString(end_date);
    }

    public void setEnd_date_string(String end_date_string) {
        this.end_date_string = end_date_string;
    }

    public Integer getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Integer begin_date) {
        this.begin_date = begin_date;
    }

    public Integer getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Integer end_date) {
        this.end_date = end_date;
    }

    public Integer getBanner_type() {
        return banner_type;
    }

    public void setBanner_type(Integer banner_type) {
        this.banner_type = banner_type;
    }

    public Integer getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(Integer banner_id) {
        this.banner_id = banner_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getIs_show() {
        return is_show;
    }

    public void setIs_show(Boolean is_show) {
        this.is_show = is_show;
    }

}
