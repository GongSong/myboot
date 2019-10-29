package com.yh.kuangjia.util;



import com.yh.kuangjia.models.Enums.AdminDeptTypeEnum;
import com.yh.kuangjia.models.Enums.BannerTypeEnum;
import com.yh.kuangjia.models.Enums.LogTypeEnum;
import com.yh.kuangjia.models.SysDictionary.SysDictionaryTypesList;

import java.util.ArrayList;
import java.util.List;

public class EnumHelper {


    /**
     * 部门类别枚举集合
     *
     * @return
     */
    public static List<SysDictionaryTypesList> GetDeptTypeEnumList() {
        List<SysDictionaryTypesList> list = new ArrayList<SysDictionaryTypesList>();
        for (AdminDeptTypeEnum type : AdminDeptTypeEnum.values()) {
            SysDictionaryTypesList item = new SysDictionaryTypesList(type.getCode(), type.getName());
            list.add(item);
        }
        return list;
    }

    /**
     * 轮播类别枚举集合
     *
     * @return
     */
    public static List<SysDictionaryTypesList> GetBannerTypeEnumList() {
        List<SysDictionaryTypesList> list = new ArrayList<SysDictionaryTypesList>();
        for ( BannerTypeEnum type : BannerTypeEnum.values()) {
            SysDictionaryTypesList item = new SysDictionaryTypesList(type.getCode(), type.getName());
            list.add(item);
        }
        return list;
    }


    /**
     * 操作日志类别枚举集合
     *
     * @return
     */
    public static List<SysDictionaryTypesList> GetLogTypeEnumList() {
        List<SysDictionaryTypesList> list = new ArrayList<SysDictionaryTypesList>();
        for ( LogTypeEnum type : LogTypeEnum.values()) {
            SysDictionaryTypesList item = new SysDictionaryTypesList(type.getCode(), type.getName());
            list.add(item);
        }
        return list;
    }


    public static class Items {
        private int type_id;
        private String type_name;

        public Items(int type_id, String type_name) {
            this.type_id = type_id;
            this.type_name = type_name;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }
    }
}
