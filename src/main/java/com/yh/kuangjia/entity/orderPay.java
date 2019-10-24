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
@TableName("order_pay")
public class orderPay extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pay_id", type = IdType.AUTO)
    private Integer pay_id;

    /**
     * 支付方式 微信 支付宝
     */
    private Integer pay_mode;

    private BigDecimal pay_amount;

    /**
     * 关联类型
     */
    private Integer link_type;

    /**
     * 关联id
     */
    private Integer link_id;

    /**
     * 预支付id
     */
    private String prepay_id;

    private String transaction_id;

    /**
     * 支付时间
     */
    private Date pay_time;

    private Integer pay_date;

    private Date create_time;


}
