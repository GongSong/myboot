package com.yh.kuangjia.util.Wx.Express;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class WxExpressCargo implements Serializable {

    /**
     * 包裹数量
     */
    private Integer count;

    /**
     * 包裹总重量，单位是千克(kg)
     */
    private BigDecimal weight;

    /**
     * 包裹长度，单位厘米(cm)
     */
    private BigDecimal space_x;

    /**
     * 包裹宽度，单位厘米(cm)
     */
    private BigDecimal space_y;

    /**
     * 包裹高度，单位厘米(cm)
     */
    private BigDecimal space_z;

    /**
     * 包裹中商品详情列表
     */
    private List<Object> detail_list;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getSpace_x() {
        return space_x;
    }

    public void setSpace_x(BigDecimal space_x) {
        this.space_x = space_x;
    }

    public BigDecimal getSpace_y() {
        return space_y;
    }

    public void setSpace_y(BigDecimal space_y) {
        this.space_y = space_y;
    }

    public BigDecimal getSpace_z() {
        return space_z;
    }

    public void setSpace_z(BigDecimal space_z) {
        this.space_z = space_z;
    }

    public List<Object> getDetail_list() {
        return detail_list;
    }

    public void setDetail_list(List<Object> detail_list) {
        this.detail_list = detail_list;
    }
}
