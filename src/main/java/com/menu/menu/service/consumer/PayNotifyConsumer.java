package com.menu.menu.service.consumer;

import com.menu.menu.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PayNotifyConsumer {

    /**
     * 监听支付通知队列
     */
    @RabbitListener(queues = RabbitMQConfig.PAY_NOTIFY_QUEUE)
    public void handlePayNotify(String message) {
        // 处理支付通知逻辑
        System.out.println("收到支付通知: " + message);
        // TODO: 实现订单状态更新、通知用户等业务逻辑
    }
}