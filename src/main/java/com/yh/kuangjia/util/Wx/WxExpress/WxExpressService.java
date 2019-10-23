package com.yh.kuangjia.util.Wx.WxExpress;

import java.io.Serializable;

public class WxExpressService implements Serializable {

    /**
     * 服务类型ID
     */
    private Integer service_type;

    /**
     * 服务名称
     */
    private String service_name;

    public Integer getService_type() {
        return service_type;
    }

    public void setService_type(Integer service_type) {
        this.service_type = service_type;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }
}
