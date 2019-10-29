package com.yh.kuangjia.models.AdminRight;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AdminRightAdd implements Serializable {
    private Integer role_id;
    private List<Integer> right_id_array;

}
