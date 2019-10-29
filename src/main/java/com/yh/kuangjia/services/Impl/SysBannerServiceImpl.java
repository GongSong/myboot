package com.yh.kuangjia.services.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.base.ResultList;
import com.yh.kuangjia.entity.SysBanner;
import com.yh.kuangjia.dao.SysBannerMapper;
import com.yh.kuangjia.models.Enums.BannerTypeEnum;
import com.yh.kuangjia.models.SingleID;
import com.yh.kuangjia.models.SysBanner.SysBannerAdd;
import com.yh.kuangjia.models.SysBanner.SysBannerDevList;
import com.yh.kuangjia.models.SysBanner.SysBannerEdit;
import com.yh.kuangjia.models.SysBanner.SysBannerFilter;
import com.yh.kuangjia.services.SysBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.kuangjia.util.ALiYun.OssUtil;
import com.yh.kuangjia.util.AdapterUtil;
import com.yh.kuangjia.util.CacheUtil;
import com.yh.kuangjia.util.DateUtil;
import com.yh.kuangjia.util.Define.DefineUtil;
import com.yh.kuangjia.util.EhCacheSpaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 任性
 * @since 2019-10-28
 */
@Service
public class SysBannerServiceImpl extends ServiceImpl<SysBannerMapper, SysBanner> implements SysBannerService {

    @Autowired
    SysBannerMapper mapper;
    @Autowired
    OssUtil ossUtil;
    @Autowired
    CacheUtil ehcacheUtil;

    private String _key = "SysBannerService";

    @Override
    public ResultList GetPage(SysBannerFilter filter) {
        QueryWrapper<SysBanner> sysBannerQueryWrapper = new QueryWrapper<>();
        if (null != filter.getKeyword() && !filter.getKeyword().equals("")) {
            sysBannerQueryWrapper.eq("title", filter.getKeyword());
        }
        if (null != filter.getBanner_type() && !filter.getBanner_type().equals(0)) {
            sysBannerQueryWrapper.eq("banner_type", filter.getBanner_type());
        }
        if (null != filter.getIs_show() && !filter.getIs_show().equals(0)) {
            sysBannerQueryWrapper.eq("is_show", filter.getIs_show().equals(1) ? true : false);
        }
        IPage<SysBanner> sysBannerIPage = mapper.selectPage(new Page<>(filter.getPage_index(), filter.getPage_size()), sysBannerQueryWrapper);
        if (filter.pageable())
            PageHelper.startPage(filter.getPage_index(), filter.getPage_size());
        List<SysBannerDevList> adapter = AdapterUtil.Adapter(sysBannerIPage.getRecords(), SysBannerDevList.class);
        if (null != adapter && adapter.size() != 0) {
            adapter.forEach(o -> {
                o.setPic(ossUtil.generatePresignedUrl(o.getPic()));
                o.setBanner_type_string(BannerTypeEnum.valueOf(o.getBanner_type()).getName());
                o.setBegin_date_string(null != o.getBegin_date() ? DateUtil.GetIntToDateString(o.getBegin_date(), "yyyy-MM-dd") : "");
                o.setEnd_date_string(null != o.getEnd_date() ? DateUtil.GetIntToDateString(o.getEnd_date(), "yyyy-MM-dd") : "");
            });
        }
        return ResultList.success(adapter, sysBannerIPage.getTotal());
    }

    @Override
    public Result Update(SingleID dto) {
        /**
         * 清除缓存
         */
        Remove();
        SysBanner entity = mapper.selectById(dto.getSingle_id());
        if (entity == null) {
            return new Result(DefineUtil.INPUT_ERROR, DefineUtil.INPUT_ERROR_MSG);
        }
        entity.setCreate_time(DateUtil.GetDate());
        entity.setIs_show(!entity.getIs_show());
        if (mapper.updateById(entity) == 0) {
            return new Result(DefineUtil.UPDATE_ERROR, DefineUtil.UPDATE_ERROR_MSG);
        }
        return Result.success(entity);
    }

    @Override
    public Result Delete(SingleID dto) {
        /**
         * 清除缓存
         */
        Remove();
        SysBanner entity = mapper.selectById(dto.getSingle_id());
        if (entity == null) {
            return new Result(DefineUtil.INPUT_ERROR, DefineUtil.INPUT_ERROR_MSG);
        }
        if (mapper.deleteById(dto.getSingle_id()) == 0) {
            return new Result(DefineUtil.DELETE_ERROR, DefineUtil.DELETE_ERROR_MSG);
        }
        return Result.success(true);
    }

    @Override
    public Result Add(SysBannerAdd dto) {
        /**
         * 清除缓存
         */
        Remove();
        SysBanner entity = new SysBanner();
        entity.setTitle(dto.getTitle());
        entity.setSort(dto.getSort());
        entity.setCreate_time(DateUtil.GetDate());
        entity.setIs_show(dto.getIs_show());
        entity.setPic(dto.getPic());
        entity.setUrl(dto.getUrl());
        entity.setBanner_type(dto.getBanner_type());
        entity.setBegin_date(dto.getBegin_date());
        entity.setEnd_date(dto.getEnd_date());
        if (mapper.insert(entity) == 0) {
            return new Result(DefineUtil.ADD_ERROR, DefineUtil.ADD_ERROR_MSG);
        }
        return Result.success(entity);
    }

    @Override
    public Result Edit(SysBannerEdit dto) {
        /**
         * 清除缓存
         */
        Remove();
        SysBanner entity = mapper.selectById(dto.getBanner_id());
        if (entity == null) {
            return new Result(DefineUtil.INPUT_ERROR, DefineUtil.INPUT_ERROR_MSG);
        }
        entity.setTitle(dto.getTitle());
        entity.setSort(dto.getSort());
        entity.setCreate_time(DateUtil.GetDate());
        entity.setIs_show(dto.getIs_show());
        entity.setPic(dto.getPic());
        entity.setUrl(dto.getUrl());
        entity.setBanner_type(dto.getBanner_type());
        entity.setBegin_date(dto.getBegin_date());
        entity.setEnd_date(dto.getEnd_date());
        if (mapper.updateById(entity) == 0) {
            return new Result(DefineUtil.UPDATE_ERROR, DefineUtil.UPDATE_ERROR_MSG);
        }
        return Result.success(entity);
    }

    private List<SysBanner> getList() {
        List<SysBanner> list;
        list = ehcacheUtil.get(EhCacheSpaces.SysBanner, _key);
        if (list == null || list.size() == 0) {
            synchronized (getClass()) {
                list = ehcacheUtil.get(EhCacheSpaces.SysBanner, _key);
                if (list == null) {
                    list = mapper.selectList(new QueryWrapper<>()).parallelStream().filter(f -> f.getIs_show().equals(true)).collect(Collectors.toList());
                    ehcacheUtil.put(EhCacheSpaces.SysBanner, _key, list);
                }
            }
        }
        return list;
    }

    private void Remove() {
        ehcacheUtil.remove(EhCacheSpaces.SysBanner, _key);
    }
}
