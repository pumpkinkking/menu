package com.menu.menu.aspect;

import com.menu.menu.annotation.DistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁AOP切面，实现基于@DistributedLock注解的分布式锁功能
 */
@Aspect
@Component
public class DistributedLockAspect {

    private static final Logger logger = LoggerFactory.getLogger(DistributedLockAspect.class);
    private static final ExpressionParser parser = new SpelExpressionParser();
    private static final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 环绕通知：处理分布式锁逻辑
     */
    @Around("@annotation(distributedLock)")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
        String lockKey = parseLockKey(joinPoint, distributedLock);
        long waitTime = distributedLock.waitTime();
        long leaseTime = distributedLock.leaseTime();
        TimeUnit timeUnit = distributedLock.timeUnit();
        String failMessage = distributedLock.failMessage();

        RLock lock = redissonClient.getLock(lockKey);
        boolean locked = false;

        try {
            // 尝试获取锁
            locked = lock.tryLock(waitTime, leaseTime, timeUnit);
            if (locked) {
                logger.info("成功获取分布式锁，锁key: {}", lockKey);
                // 执行目标方法
                return joinPoint.proceed();
            } else {
                logger.warn("获取分布式锁失败，锁key: {}", lockKey);
                throw new RuntimeException(failMessage);
            }
        } catch (InterruptedException e) {
            logger.error("获取分布式锁被中断，锁key: {}", lockKey, e);
            Thread.currentThread().interrupt();
            throw new RuntimeException("获取分布式锁被中断，请稍后重试");
        } finally {
            // 确保锁释放
            if (locked && lock.isHeldByCurrentThread()) {
                lock.unlock();
                logger.info("释放分布式锁，锁key: {}", lockKey);
            }
        }
    }

    /**
     * 解析SpEL表达式生成锁key
     */
    private String parseLockKey(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) {
        String lockKeyExpression = distributedLock.lockKey();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();

        // 解析SpEL表达式
        EvaluationContext context = new StandardEvaluationContext();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        if (parameterNames != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
        }

        return parser.parseExpression(lockKeyExpression).getValue(context, String.class);
    }
}