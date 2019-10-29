package com.yh.kuangjia.dao;

import com.yh.kuangjia.entity.AdminRightGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yh.kuangjia.models.AdminRight.AdminRightGroupList;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 任性
 * @since 2019-10-25
 */
public interface AdminRightGroupMapper extends BaseMapper<AdminRightGroup> {

    List<AdminRightGroupList> selectGroupNameAndGroupid();
}
