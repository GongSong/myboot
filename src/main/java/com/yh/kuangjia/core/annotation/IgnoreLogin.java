package com.yh.kuangjia.core.annotation;

import java.lang.annotation.*;

/**
 * 不需要登录注解
 */

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreLogin {
    boolean ignore() default true;
}
