package com.menu.menu.service.impl;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.menu.menu.config.RabbitMQConfig;
import com.menu.menu.annotation.DistributedLock;
import com.menu.menu.service.PayService;
import com.menu.menu.service.strategy.PayStrategy;
import com.menu.menu.service.strategy.PayStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.menu.menu.service.RabbitMQSender;
import java.util.concurrent.TimeUnit;

@Service
public class PayServiceImpl implements PayService {

    private final PayStrategyFactory payStrategyFactory;
    private final RabbitMQSender rabbitMQSender;

    @Autowired
    public PayServiceImpl(PayStrategyFactory payStrategyFactory, RabbitMQSender rabbitMQSender) {
        this.payStrategyFactory = payStrategyFactory;
        this.rabbitMQSender = rabbitMQSender;
    }

    @Override
    public WxPayMpOrderResult createOrder(String orderNo, Integer totalFee, String openid, String body) throws WxPayException {
        // 默认使用微信支付策略
        PayStrategy strategy = payStrategyFactory.getStrategy("wx");
        return strategy.createOrder(orderNo, totalFee, openid, body);
    }

    @DistributedLock(lockKey = "'pay_notify_' + #xmlData.hashCode()", leaseTime = 10, timeUnit = TimeUnit.SECONDS)
    @Override
    public String handlePayNotify(String xmlData) throws WxPayException {
        PayStrategy strategy = payStrategyFactory.getStrategy("wx");
        String result = strategy.handlePayNotify(xmlData);
        
        // 发送支付结果到消息队列异步处理后续业务
        rabbitMQSender.sendMessage(RabbitMQConfig.PAY_EXCHANGE, RabbitMQConfig.PAY_ROUTING_KEY, xmlData);
        
        return result;
    }
}