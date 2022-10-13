package com.qks.anotation.another;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 年龄校验
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Inherited
public @interface ValidateAge {
    /**
     * 最小值
     */
    int min() default 18;

    /**
     * 最大值
     */
    int max() default 99;

    /**
     * 默认值
     */
    int defaultValue() default 20;
}