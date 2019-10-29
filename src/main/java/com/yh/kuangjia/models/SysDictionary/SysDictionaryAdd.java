package com.yh.kuangjia.models.SysDictionary;

import com.yh.kuangjia.core.annotation.ParamCheck;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysDictionaryAdd   implements Serializable {
    private Integer dict_id ;
    private Integer dict_type ;
    @ParamCheck(notNull=true,message = "字典名称不能为空")
    private String dict_name ;
    private String dict_value ;
    private String dict_code ;
    private boolean is_disabled ;
    private Integer sort ;

}
