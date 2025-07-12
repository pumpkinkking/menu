package com.menu.menu.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitMQConfig {

    // 支付通知队列
    public static final String PAY_NOTIFY_QUEUE = "pay.notify.queue";
    // 支付交换机
    public static final String PAY_EXCHANGE = "pay.exchange";
    // 支付路由键
    public static final String PAY_ROUTING_KEY = "pay.notify";

    // 创建队列
    // 死信交换机
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange("dead_letter_exchange", true, false);
    }

    // 死信队列
    @Bean
    public Queue deadLetterQueue() {
        return new Queue("dead_letter_queue", true);
    }

    // 死信队列绑定
    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("dead_letter_routing_key");
    }

    // 支付通知队列（设置死信参数）
    @Bean
    public Queue payNotifyQueue() {
        Map<String, Object> arguments = new HashMap<>();
        // 消息过期后发送到死信交换机
        arguments.put("x-dead-letter-exchange", "dead_letter_exchange");
        // 死信路由键
        arguments.put("x-dead-letter-routing-key", "dead_letter_routing_key");
        // 队列消息过期时间（60秒）
        arguments.put("x-message-ttl", 60000);
        return new Queue(PAY_NOTIFY_QUEUE, true, false, false, arguments);
    }

    // 创建交换机
    @Bean
    public DirectExchange payExchange() {
        return new DirectExchange(PAY_EXCHANGE, true, false);
    }

    // 重试模板配置
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        // 指数退避策略
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(1000); // 初始间隔1秒
        backOffPolicy.setMultiplier(2); // 倍数2
        backOffPolicy.setMaxInterval(5000); // 最大间隔5秒
        retryTemplate.setBackOffPolicy(backOffPolicy);

        // 重试策略：最多重试3次
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }

    // 配置监听器容器工厂，添加重试机制
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, RetryTemplate retryTemplate) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setRetryTemplate(retryTemplate);
        // 重试耗尽后拒绝消息并发送到死信队列
        factory.setErrorHandler(new ConditionalRejectingErrorHandler(new FatalExceptionStrategy() {
            @Override
            public boolean isFatal(Throwable t) {
                return false;
            }
        }));
        return factory;
    }

    // 绑定队列和交换机
    @Bean
    public Binding bindPayQueue() {
        return BindingBuilder.bind(payNotifyQueue()).to(payExchange()).with(PAY_ROUTING_KEY);
    }
}