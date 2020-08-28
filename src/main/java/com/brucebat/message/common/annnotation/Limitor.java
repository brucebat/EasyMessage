package com.brucebat.message.common.annnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author Sun Tianyu
 * @version 1.0
 * @date Created in 2020/8/28
 * @description
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Limitor {

    /**
     * 单位时间并发个数
     */
    double limit() default 1;

    /**
     * 时间单位
     */
    TimeUnit timeUint() default TimeUnit.SECONDS;
}
