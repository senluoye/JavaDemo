package com.qks.anotation.another;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 性别赋值
 * @author 15998
 */
@Documented // 是否包含于JavaDoc中
@Retention(RetentionPolicy.RUNTIME) // 注解生命周期
@Target({ ElementType.FIELD, ElementType.METHOD }) // 注解作用范围
@Inherited // 是否可以继承
public @interface InitSex {
    /**
     * sex enum
     */
    enum SEX_TYPE {
        MAN,
        WOMAN
    }

    SEX_TYPE sex() default SEX_TYPE.MAN;
}