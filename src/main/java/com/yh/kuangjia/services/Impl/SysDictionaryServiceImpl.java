package com.yh.kuangjia.services.Impl;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.entity.SysDictionary;
import com.yh.kuangjia.dao.SysDictionaryMapper;
import com.yh.kuangjia.models.SingleID;
import com.yh.kuangjia.models.SysDictionary.SysDictionaryAdd;
import com.yh.kuangjia.models.SysDictionary.SysDictionaryUpdate;
import com.yh.kuangjia.services.SysDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.kuangjia.util.CacheUtil;
import com.yh.kuangjia.util.Define.DefineUtil;
import com.yh.kuangjia.util.EhCacheSpaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.schema.Collections;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author 任性
 * @since 2019-10-29
 */
@Service
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryMapper, SysDictionary> implements SysDictionaryService {
    @Autowired
    SysDictionaryMapper mapper;
    @Autowired
    CacheUtil ehcacheUtil;
    private String _key = "SysDictionaryService";


    public List<SysDictionary> GetList() {
        List<SysDictionary> list;
        list = ehcacheUtil.get(EhCacheSpaces.DictionaryCache, _key);
        if (list == null || list.size() == 0) {
            synchronized (getClass()) {
                list = ehcacheUtil.get(EhCacheSpaces.DictionaryCache, _key);
                if (list == null) {
                    list = mapper.selectList(null);
                    ehcacheUtil.put(EhCacheSpaces.DictionaryCache, _key, list);
                }
            }
        }
        return list;
    }

    private void RemoveListCache() {
        ehcacheUtil.remove(EhCacheSpaces.DictionaryCache, _key);
    }

    @Override
    public Result GetDevList(int dicttype) {
        return Result.success(GetList().stream().filter(f->f.getDict_type().equals(dicttype)).collect(Collectors.toList()));
    }

    @Override
    public Result Delete(SingleID dto) {
        SysDictionary entity = mapper.selectById(dto.getSingle_id());
        if (entity == null) {
            return new Result(DefineUtil.INPUT_ERROR, DefineUtil.INPUT_ERROR_MSG);
        }
        if (mapper.deleteById(dto.getSingle_id()) == 0) {
            return new Result(DefineUtil.DELETE_ERROR, DefineUtil.DELETE_ERROR_MSG);
        }
        RemoveListCache();
        return Result.success(true);
    }

    @Override
    public Result Add(Integer adminID, SysDictionaryAdd dto) {
        SysDictionary entity = new SysDictionary();
        List<SysDictionary> collect = GetList().parallelStream().filter(f -> f.getDict_type().toString().equals(dto.getDict_type()+"")).collect(Collectors.toList());
        if (null!=collect&&collect.size()!=0) {
            collect.sort((h1,h2)->h2.getDict_code().compareTo(h1.getDict_code()));
            SysDictionary sysDictionary = collect.parallelStream().findFirst().orElse(null);
            if (null==sysDictionary) {
                entity.setDict_code(sysDictionary.getDict_type()+"001");
            } else {
                entity.setDict_code(Integer.parseInt(sysDictionary.getDict_code())+1+"");
            }
        } else {
            if (String.valueOf(dto.getDict_type()).length()>=2) {
                entity.setDict_code(dto.getDict_type()+"01");
            } else {
                entity.setDict_code(dto.getDict_type()+"001");
            }
        }
        entity.setDict_name(dto.getDict_name());
        entity.setDict_type(dto.getDict_type());
        entity.setDict_value(dto.getDict_value());
        entity.setSort(dto.getSort());
        entity.setIs_disabled(false);
        if (mapper.insert(entity) == 0) {
            return new Result(DefineUtil.ADD_ERROR, DefineUtil.ADD_ERROR_MSG);
        }
        RemoveListCache();
        return Result.success(entity);
    }

    @Override
    public Result Update(Integer adminID, SysDictionaryUpdate dto) {
        SysDictionary entity = mapper.selectById(dto.getDict_id());
        if (null==entity  ) {
            return new Result(DefineUtil.INPUT_ERROR, DefineUtil.INPUT_ERROR_MSG);
        }
        entity.setDict_code(dto.getDict_code());
        entity.setDict_name(dto.getDict_name());
        entity.setDict_type(dto.getDict_type());
        entity.setDict_value(dto.getDict_value());
        entity.setSort(dto.getSort());
        if (mapper.updateById(entity) == 0) {
            return new Result(DefineUtil.UPDATE_ERROR, DefineUtil.UPDATE_ERROR_MSG);
        }
        RemoveListCache();
        return Result.success(entity);
    }
}
