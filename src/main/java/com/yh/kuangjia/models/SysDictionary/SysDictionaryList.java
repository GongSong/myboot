package com.yh.kuangjia.models.SysDictionary;

import java.io.Serializable;

public class SysDictionaryList implements Serializable {

    public SysDictionaryList(Integer dictId, String dictCode, String dictName, Byte dictType, String dictValue, Boolean isDisabled, Integer sort) {
        this.dict_id = dictId;
        this.dict_type = dictType;
        this.dict_name = dictName;
        this.dict_code = dictCode;
        this.dict_value = dictValue;
        this.is_disabled = isDisabled;
        this.sort = sort;
    }

    /**
     * 字典id
     */
    private Integer dict_id;

    /**
     * 字典类型（枚举）
     */
    private Byte dict_type;

    /**
     * 字典名称
     */
    private String dict_name;

    /**
     * 字典代号
     */
    private String dict_code;

    /**
     * 字典值
     */
    private String dict_value;

    /**
     * 是否禁用
     */
    private Boolean is_disabled;

    /**
     * 排序
     */
    private Integer sort;

    private static final long serialVersionUID = 1L;

    public Integer getDict_id() {
        return dict_id;
    }

    public void setDict_id(Integer dict_id) {
        this.dict_id = dict_id;
    }

    public Byte getDict_type() {
        return dict_type;
    }

    public void setDict_type(Byte dict_type) {
        this.dict_type = dict_type;
    }

    public String getDict_name() {
        return dict_name;
    }

    public void setDict_name(String dict_name) {
        this.dict_name = dict_name == null ? null : dict_name.trim();
    }

    public String getDict_code() {
        return dict_code;
    }

    public void setDict_code(String dict_code) {
        this.dict_code = dict_code == null ? null : dict_code.trim();
    }

    public String getDict_value() {
        return dict_value;
    }

    public void setDict_value(String dict_value) {
        this.dict_value = dict_value == null ? null : dict_value.trim();
    }

    public Boolean getIs_disabled() {
        return is_disabled;
    }

    public void setIs_disabled(Boolean is_disabled) {
        this.is_disabled = is_disabled;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
