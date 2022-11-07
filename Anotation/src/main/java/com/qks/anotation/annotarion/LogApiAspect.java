package com.qks.anotation.annotarion;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * 解析注解主要通过aop实现
 */
@Aspect
@Component
public class LogApiAspect {

    /**
     * @Pointcut 定义切入点
     * <p>切入点方法应该是空体</>
     */
    @Pointcut("@annotation(com.qks.anotation.annotarion.LogApi)")
    public void logApi() {
    }

    /**
     * 对切入点方法的环绕通知
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logApi()")
    @SuppressWarnings("unchecked")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Class type = joinPoint.getSignature().getDeclaringType();
        String typeName = type.getSimpleName();
        Logger logger = LoggerFactory.getLogger(type);
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class[] clazz = new Class[args.length];

        for (int i = 0; i < args.length; i++) {
            clazz[i] = args[i].getClass();
        }

        Method method = type.getMethod(methodName, clazz);
        Parameter[] parameters = method.getParameters();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            sb.append(parameters[i].getName()).append("-").append(args[i]).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.lastIndexOf(","));
        }

        Object res;
        try {
            res = joinPoint.proceed();
            logger.info("调用{}.{}方法成功, 参数为[{}], 返回结果[{}]", typeName, method, sb,
                    JSONObject.toJSONString(res));
        } catch (Exception e) {
            logger.error("调用{}.{}方法异常", typeName, methodName);
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            logger.info("调用{}.{}方法耗时[{}]ms", typeName, method, endTime - startTime);
        }

        return res;
    }
}
