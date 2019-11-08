package com.yh.kuangjia.test.ES;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description:定义ItemRepository 接口
 * @Param:
 * 	Item:为实体类
 * 	Long:为Item实体类中主键的数据类型
 * @Author: https://blog.csdn.net/chen_2890
 * @Date: 2018/9/29 0:50
 */

@Component
@Service("ItemRepository")
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

}
