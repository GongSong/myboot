package com.yh.kuangjia.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yh.kuangjia.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 任性
 * @since 2019-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wl_service")
public class WlService extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "service_id", type = IdType.AUTO)
    private Integer service_id;

    /**
     * 服务名称
     */
    private String service_name;

    /**
     * 合同名称
     */
    private String contract_name;

    /**
     * 合同图片
     */
    private String contract_imgs;

    /**
     * 原价
     */
    private BigDecimal original_price;

    /**
     * 价格
     */
    private BigDecimal service_price;

    /**
     * 购买须知
     */
    private String purchase_instructions;

    private Date create_time;


}
