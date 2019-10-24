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
 * 
 * </p>
 *
 * @author 任性
 * @since 2019-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wl_user")
public class WlUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer user_id;

    private String open_id;

    private String nick_name;

    /**
     * 姓名
     */
    private String real_name;

    /**
     * 服务对象 0公司 1个人 2其他
     */
    private Integer company_type;

    /**
     * 公司
     */
    private String company_name;

    private String avatar_url;

    private Boolean gender;

    private String mobile;

    private Integer create_date;

    private Date create_time;

    private Date last_login_time;


}
