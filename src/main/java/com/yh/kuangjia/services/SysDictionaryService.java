package com.yh.kuangjia.services;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.entity.SysDictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.kuangjia.models.SingleID;
import com.yh.kuangjia.models.SysDictionary.SysDictionaryAdd;
import com.yh.kuangjia.models.SysDictionary.SysDictionaryUpdate;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author 任性
 * @since 2019-10-29
 */
public interface SysDictionaryService extends IService<SysDictionary> {

    Result GetDevList(int  dicttype);

    Result Delete(SingleID dto);

    Result Add(Integer adminID, SysDictionaryAdd dto);

    Result Update(Integer adminID, SysDictionaryUpdate dto);

}
