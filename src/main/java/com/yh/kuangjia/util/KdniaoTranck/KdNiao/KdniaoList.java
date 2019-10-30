package com.yh.kuangjia.util.KdniaoTranck.KdNiao;

import java.io.Serializable;

public class KdniaoList implements Serializable {
    private String datetime;

    private String context;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "KdniaoList{" +
                "datetime='" + datetime + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
