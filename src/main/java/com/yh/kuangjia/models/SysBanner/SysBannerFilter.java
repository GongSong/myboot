package com.yh.kuangjia.models.SysBanner;

import com.yh.kuangjia.base.PageInfo;
import lombok.Data;

import java.io.Serializable;
@Data
public class SysBannerFilter extends PageInfo implements Serializable {

    private Integer banner_type;

    private Integer is_show;
}
