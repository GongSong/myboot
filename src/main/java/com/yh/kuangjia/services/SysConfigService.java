package com.yh.kuangjia.services;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.entity.SysConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.kuangjia.models.SysConfig.SysConfigUpdate;

/**
 * <p>
 * 平台配置 服务类
 * </p>
 *
 * @author 任性
 * @since 2019-10-29
 */
public interface SysConfigService extends IService<SysConfig> {

    Result GetList(int type);

    Result Update(SysConfigUpdate dto);
}
