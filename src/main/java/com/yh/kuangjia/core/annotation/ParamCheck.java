package com.yh.kuangjia.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * "验证接口参数
 */
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamCheck {
    /**
     * 是否非空
     */
    boolean notNull() default false;

    /**
     * 是否为数值
     */
    boolean numeric() default false;

    /**
     * 最大长度
     */
    int maxLen() default -1;

    /**
     * 最小长度
     */
    int minLen() default -1;

    /**
     * 最小数值
     */
    long minNum() default -999999;

    String message() default "不能为空";
}

