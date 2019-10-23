package com.yh.kuangjia.util.Wx.WxExpress;

import java.io.Serializable;

public class WxExpressSender implements Serializable {

    /**
     * 发件人姓名
     */
    private String name;

    /**
     * 发件人手机号码
     */
    private String mobile;

    /**
     * 发件人省份
     */
    private String province;

    /**
     * 发件人市、地区
     */
    private String city;

    /**
     * 发件人区、县
     */
    private String area;

    /**
     * 发件人详细地址
     */
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
