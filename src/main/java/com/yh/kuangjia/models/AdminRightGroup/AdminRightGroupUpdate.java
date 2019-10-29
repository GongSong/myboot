package com.yh.kuangjia.models.AdminRightGroup;

import com.yh.kuangjia.entity.AdminRightGroup;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdminRightGroupUpdate extends AdminRightGroup  implements Serializable {
    private Boolean is_ok;
}
