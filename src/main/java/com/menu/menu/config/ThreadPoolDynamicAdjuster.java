package com.menu.menu.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executor;

/**
 * 线程池动态调整器，根据监控指标自动调整线程池参数
 */
@Component
public class ThreadPoolDynamicAdjuster {

    @Autowired
    private Executor taskExecutor;

    @Autowired
    private MeterRegistry meterRegistry;

    // 初始核心线程数
    private static final int INITIAL_CORE_POOL_SIZE = 5;
    // 最大核心线程数限制
    private static final int MAX_CORE_POOL_SIZE = 20;
    // 队列阈值系数
    private static final double QUEUE_THRESHOLD_FACTOR = 2.0;
    // 最小队列阈值系数
    private static final double MIN_QUEUE_THRESHOLD_FACTOR = 0.5;

    /**
     * 每30秒检查并调整线程池参数
     */
    @Scheduled(fixedRate = 30000)
    public void adjustThreadPool() {
        if (taskExecutor instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor executor = (ThreadPoolExecutor) taskExecutor;

            int corePoolSize = executor.getCorePoolSize();
            int maximumPoolSize = executor.getMaximumPoolSize();
            int queueSize = executor.getQueue().size();
            int activeCount = executor.getActiveCount();
            int poolSize = executor.getPoolSize();

            System.out.printf("线程池状态 - 核心线程数: %d, 活动线程数: %d, 池大小: %d, 队列大小: %d%n",
                    corePoolSize, activeCount, poolSize, queueSize);

            // 动态调整核心线程数策略
            // 1. 当队列大小超过核心线程数的2倍且核心线程数小于最大值时，增加核心线程数
            if (queueSize > corePoolSize * QUEUE_THRESHOLD_FACTOR && corePoolSize < MAX_CORE_POOL_SIZE) {
                int newCoreSize = Math.min(corePoolSize + 2, MAX_CORE_POOL_SIZE);
                executor.setCorePoolSize(newCoreSize);
                System.out.printf("增加核心线程数: %d -> %d%n", corePoolSize, newCoreSize);
            }
            // 2. 当队列大小小于核心线程数的50%且核心线程数大于初始值时，减少核心线程数
            else if (queueSize < corePoolSize * MIN_QUEUE_THRESHOLD_FACTOR && corePoolSize > INITIAL_CORE_POOL_SIZE) {
                int newCoreSize = Math.max(corePoolSize - 1, INITIAL_CORE_POOL_SIZE);
                executor.setCorePoolSize(newCoreSize);
                System.out.printf("减少核心线程数: %d -> %d%n", corePoolSize, newCoreSize);
            }
        }
    }
}