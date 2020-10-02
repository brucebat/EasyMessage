package com.brucebat.message.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @author: Sun Tianyu
 * @date: Created in 2020/9/1
 * @description
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Limiter {

    /**
     * 限流器名称
     */
    String name() default "default";

    /**
     * 单位时间并发个数
     */
    double permitsPerSecond() default 1;

    /**
     * 限流器启动时间
     */
    long warmupPeriod() default 1;

    /**
     * 时间单位
     */
    TimeUnit timeUint() default TimeUnit.SECONDS;
}