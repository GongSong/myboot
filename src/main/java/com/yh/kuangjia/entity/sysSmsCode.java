package com.yh.kuangjia.entity;

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
 * 短信发送日志
 * </p>
 *
 * @author 任性
 * @since 2019-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_sms_code")
public class sysSmsCode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer log_id;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 短信验证码
     */
    private String code;

    /**
     * 错误次数
     */
    private Integer times;

    /**
     * 添加时间
     */
    private Date create_time;


}
