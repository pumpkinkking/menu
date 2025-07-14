package com.menu.menu.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson配置类，用于分布式锁、分布式对象等功能
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.data.redis.host:localhost}")
    private String redisHost;

    @Value("${spring.data.redis.port:6379}")
    private int redisPort;

    @Value("${spring.data.redis.password:}")
    private String redisPassword;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 单机模式配置
        String redisAddress = String.format("redis://%s:%d", redisHost, redisPort);
        config.useSingleServer()
                .setAddress(redisAddress)
                .setPassword(redisPassword.isEmpty() ? null : redisPassword)
                .setConnectionPoolSize(10)
                .setConnectionMinimumIdleSize(5);

        // 集群模式配置示例（如需可启用）
        // config.useClusterServers()
        //         .addNodeAddress("redis://127.0.0.1:7001", "redis://127.0.0.1:7002")
        //         .setPassword(redisPassword);

        return Redisson.create(config);
    }
}