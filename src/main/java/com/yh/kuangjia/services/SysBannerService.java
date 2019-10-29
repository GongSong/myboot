package com.yh.kuangjia.services;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.base.ResultList;
import com.yh.kuangjia.entity.SysBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.kuangjia.models.SingleID;
import com.yh.kuangjia.models.SysBanner.SysBannerAdd;
import com.yh.kuangjia.models.SysBanner.SysBannerEdit;
import com.yh.kuangjia.models.SysBanner.SysBannerFilter;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 任性
 * @since 2019-10-28
 */
public interface SysBannerService extends IService<SysBanner> {

    ResultList GetPage(SysBannerFilter filter);

    Result Update(SingleID dto);

    Result Delete(SingleID dto);

    Result Add(SysBannerAdd dto);

    Result Edit(SysBannerEdit dto);
}
