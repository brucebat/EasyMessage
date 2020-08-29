package com.brucebat.message.service.limiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Sun Tianyu
 * @version 1.0
 * @date Created in 2020/8/29
 * @description
 */
@SuppressWarnings("UnstableApiUsage")
public class RateLimiterHelper {

    private static final ConcurrentHashMap<String, RateLimiter> RATE_LIMITER_MAP = new ConcurrentHashMap<>();

    /**
     * 设置限流器
     *
     * @param name 限流器名称
     * @param rateLimiter 限流器
     * @return 限流器
     */
    public static RateLimiter setRateLimiter(String name, RateLimiter rateLimiter) {
        if (RATE_LIMITER_MAP.containsKey(name)) {
            return RATE_LIMITER_MAP.get(name);
        }
        RATE_LIMITER_MAP.put(name, rateLimiter);
        return rateLimiter;
    }

    /**
     * 根据名称获取限流器
     *
     * @param name 名称
     * @return 限流器
     */
    public static RateLimiter getRateLimiter(String name) {
        return RATE_LIMITER_MAP.get(name);
    }

    /**
     * 进行限流器数据更新
     *
     * @param name 限流器名称
     * @param rateLimiter 限流器
     */
    public static void refresh(String name, RateLimiter rateLimiter) {
        RATE_LIMITER_MAP.remove(name);
        setRateLimiter(name, rateLimiter);
    }
}
