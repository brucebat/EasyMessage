package com.brucebat.message.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author Sun Tianyu
 * @version 1.0
 * @date Created in 2020/8/28
 * @description
 */
@Aspect
public class RateLimitAspect {

    /**
     * 环绕增强
     *
     * @param joinPoint 切点
     * @return 返回参数
     */
    @Around("@within(com.brucebat.message.common.annnotation.Limitor) || @annotation(com.brucebat.message.common.annnotation.Limitor)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (Objects.isNull(methodSignature)) {
            return joinPoint.proceed();
        }
        Method method = methodSignature.getMethod();
        if (Objects.isNull(method)) {
            return joinPoint.proceed();
        }
        return null;
    }
}
