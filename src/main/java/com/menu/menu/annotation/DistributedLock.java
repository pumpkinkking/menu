package com.menu.menu.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁注解，用于标记需要分布式锁保护的核心业务方法
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {

    /**
     * 锁的key，支持SpEL表达式
     */
    String lockKey();

    /**
     * 锁的过期时间，默认30秒
     */
    long leaseTime() default 30;

    /**
     * 时间单位，默认秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 获取锁的最大等待时间，默认5秒
     */
    long waitTime() default 5;

    /**
     * 获取锁失败时的提示信息
     */
    String failMessage() default "获取分布式锁失败，请稍后重试";
}