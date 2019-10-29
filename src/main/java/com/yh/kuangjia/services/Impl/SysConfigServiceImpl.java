package com.yh.kuangjia.services.Impl;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.entity.SysConfig;
import com.yh.kuangjia.dao.SysConfigMapper;
import com.yh.kuangjia.models.SysConfig.SysConfigUpdate;
import com.yh.kuangjia.services.SysConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.kuangjia.util.CacheUtil;
import com.yh.kuangjia.util.Define.DefineUtil;
import com.yh.kuangjia.util.EhCacheSpaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 平台配置 服务实现类
 * </p>
 *
 * @author 任性
 * @since 2019-10-29
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

    @Autowired
    SysConfigMapper mapper;
    private String _key = "SysConfigService";
    @Autowired
    CacheUtil ehcacheUtil;

    @Override
    public Result GetList(int type) {
        return Result.success(GetList().stream().filter(f->f.getType().equals(type)).collect(Collectors.toList()));
    }

    @Override
    public Result Update(SysConfigUpdate dto) {
        RemoveListCache();
        if (mapper.updateById(dto)==0) return new Result(DefineUtil.UPDATE_ERROR,DefineUtil.UPDATE_ERROR_MSG);
        return Result.success();
    }

    private List<SysConfig> GetList() {
        List<SysConfig> list;
        list = ehcacheUtil.get(EhCacheSpaces.ConfigCache, _key);
        if (list == null || list.size() == 0) {
            synchronized (getClass()) {
                list = ehcacheUtil.get(EhCacheSpaces.ConfigCache, _key);
                if (list == null) {
                    list = mapper.selectList(null);
                    ehcacheUtil.put(EhCacheSpaces.ConfigCache, _key, list);
                }
            }
        }
        return list;
    }

    private void RemoveListCache() {
        ehcacheUtil.remove(EhCacheSpaces.ConfigCache, _key);
    }
}
