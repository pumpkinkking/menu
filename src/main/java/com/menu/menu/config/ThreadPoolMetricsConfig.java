package com.menu.menu.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

/**
 * 线程池监控配置，将线程池指标注册到Micrometer
 */
@Configuration
public class ThreadPoolMetricsConfig {

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private Executor taskExecutor;

    /**
     * 注册线程池指标
     */
    @PostConstruct
    public void registerThreadPoolMetrics() {
        // 为taskExecutor线程池注册指标
        new ExecutorServiceMetrics((java.util.concurrent.ThreadPoolExecutor) taskExecutor, "taskExecutor", null)
                .bindTo(meterRegistry);
        System.out.println("线程池指标已注册到Micrometer");
    }
}