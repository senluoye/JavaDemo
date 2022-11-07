package com.qks.anotation.annotarion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于日志记录的注解
 * 这种没有属性值的注解属于标记注解
 * @author 15998
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME) // 为了通过反射获取注解信息，所以必须是RUNTIME
public @interface LogApi {
}
