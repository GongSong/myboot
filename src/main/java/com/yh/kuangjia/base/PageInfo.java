package com.yh.kuangjia.base;

import java.io.Serializable;

/**
 * 页码信息
 */
public class PageInfo implements Serializable {

    /**
     * 当前页码
     */
    private Integer page_index;

    /**
     * 每页返回数据数量
     */
    private Integer page_size;

    /**
     * 跳过记录数
     */
    private Integer skip;

    /**
     * 返回记录数
     */
    private Integer take;

    /**
     * 时间字段
     */
    private Integer time_field;

    /**
     * 起始日期
     */
    private Integer begin_date;

    /**
     * 截止日期
     */
    private Integer end_date;

    /**
     * 关键词字段
     */
    private Integer field;

    /**
     * 搜索关键字
     */
    private String keyword;

    /**
     * 是否显示
     */
    private Integer is_show;

    /**
     * 排序字段
     */
    private Integer order_field;

    /**
     * 是否降序
     */
    private Boolean is_desc;

    private long totalCount;

    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getTake() {
        return take;
    }

    public void setTake(Integer take) {
        this.take = take;
    }

    public Integer getTime_field() {
        return time_field;
    }

    public void setTime_field(Integer time_field) {
        this.time_field = time_field;
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

    public Integer getField() {
        return field;
    }

    public void setField(Integer field) {
        this.field = field;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }

    public Integer getOrder_field() {
        return order_field;
    }

    public void setOrder_field(Integer order_field) {
        this.order_field = order_field;
    }

    public Boolean getIs_desc() {
        return is_desc;
    }

    public void setIs_desc(Boolean is_desc) {
        this.is_desc = is_desc;
    }


    public boolean pageable() {
        return page_size > 0 && page_index >= 0;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
