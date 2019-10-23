package com.yh.kuangjia.models.SysRight;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysRightList implements Serializable {

    private String icon;
    private String index;
    private String path;
    private String title;

}
