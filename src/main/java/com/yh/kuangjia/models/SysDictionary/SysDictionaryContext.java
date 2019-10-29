package com.yh.kuangjia.models.SysDictionary;

import java.io.Serializable;

public class SysDictionaryContext implements Serializable {
    /**
     * 字典名称
     */
    private String dict_name;

    /**
     * 字典代号
     */
    private String dict_code;

    public String getDict_name() {
        return dict_name;
    }

    public void setDict_name(String dict_name) {
        this.dict_name = dict_name;
    }

    public String getDict_code() {
        return dict_code;
    }

    public void setDict_code(String dict_code) {
        this.dict_code = dict_code;
    }
}
