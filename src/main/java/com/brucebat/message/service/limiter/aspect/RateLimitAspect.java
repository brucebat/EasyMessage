package com.brucebat.message.service.limiter.aspect;

import com.brucebat.message.common.annotation.Limiter;
import com.brucebat.message.service.limiter.RateLimiterHelper;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @since : Created in 2020/9/1
 *
 */
@Aspect
public class RateLimitAspect {

    private static final Logger log = LoggerFactory.getLogger(RateLimitAspect.class);

    /**
     * 环绕增强
     *
     * @param joinPoint 切点
     * @return 返回参数
     * @throws Throwable 运行时异常
     */
    @SuppressWarnings("UnstableApiUsage")
    @Around("@within(com.brucebat.message.common.annotation.Limiter) || @annotation(com.brucebat.message.common.annotation.Limiter)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (Objects.isNull(methodSignature)) {
            return joinPoint.proceed();
        }
        Method method = methodSignature.getMethod();
        if (Objects.isNull(method)) {
            return joinPoint.proceed();
        }
        Limiter limiter = method.getAnnotation(Limiter.class);
        if (Objects.isNull(limiter)) {
            limiter = joinPoint.getTarget().getClass().getAnnotation(Limiter.class);
        }
        String name = limiter.name();
        RateLimiter rateLimiter = RateLimiterHelper.getRateLimiter(name);
        if (Objects.isNull(rateLimiter)) {
            double permitsPerSecond = limiter.permitsPerSecond();
            long warmupPeriod = limiter.warmupPeriod();
            TimeUnit timeUnit = limiter.timeUint();
            log.info("创建限流器, 参数为permitsPerSecond = {}, warmupPeriod = {}, timeUnit = {}", permitsPerSecond, warmupPeriod, timeUnit);
            rateLimiter = RateLimiterHelper.setRateLimiter(name, RateLimiter.create(permitsPerSecond, warmupPeriod, timeUnit));
        }
        if (rateLimiter.tryAcquire()) {
            return joinPoint.proceed();
        } else {
            throw new RuntimeException("未获取到限流器执行令牌");
        }
    }
}
