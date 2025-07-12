package com.menu.menu.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;

/**
 * 缓存降级切面，当Redis不可用时自动降级到数据库查询
 */
@Aspect
@Component
public class CacheDegradationAspect {

    private static final Logger logger = LoggerFactory.getLogger(CacheDegradationAspect.class);

    /**
     * 切入点：匹配所有带@Cacheable注解的方法
     */
    @Pointcut("@annotation(org.springframework.cache.annotation.Cacheable)")
    public void cacheablePointcut() {}

    /**
     * 环绕通知：处理缓存降级逻辑
     */
    @Around("cacheablePointcut()")
    public Object handleCacheDegradation(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 尝试执行原方法（正常走缓存逻辑）
            return joinPoint.proceed();
        } catch (RedisConnectionFailureException e) {
            // Redis连接失败，执行降级策略
            logger.error("Redis connection failed, triggering cache degradation: {}", e.getMessage());
            // 直接执行目标方法（不经过缓存）
            return proceedWithoutCache(joinPoint);
        } catch (Exception e) {
            // 其他异常，判断是否与Redis相关
            if (isRedisRelatedException(e)) {
                logger.error("Redis error occurred, triggering cache degradation: {}", e.getMessage());
                return proceedWithoutCache(joinPoint);
            }
            // 非Redis异常，继续抛出
            throw e;
        }
    }

    /**
     * 不经过缓存直接执行目标方法
     */
    private Object proceedWithoutCache(ProceedingJoinPoint joinPoint) throws Throwable {
        // 可以在这里添加降级日志、监控等
        logger.warn("Executing method without cache: {}.{}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
        return joinPoint.proceed();
    }

    /**
     * 判断异常是否与Redis相关
     */
    private boolean isRedisRelatedException(Throwable e) {
        if (e == null) return false;
        // 检查异常类型或消息是否包含Redis相关关键字
        return e.getClass().getName().contains("redis") ||
               (e.getMessage() != null && e.getMessage().toLowerCase().contains("redis")) ||
               isRedisRelatedException(e.getCause());
    }
}