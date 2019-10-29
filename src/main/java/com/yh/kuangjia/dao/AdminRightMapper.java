package com.yh.kuangjia.dao;

import com.yh.kuangjia.entity.AdminRight;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yh.kuangjia.models.AdminRight.AdminRightList;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 任性
 * @since 2019-10-28
 */
public interface AdminRightMapper extends BaseMapper<AdminRight> {
    List<AdminRightList> selectInfo();

}
