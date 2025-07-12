package com.menu.menu.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PayStrategyFactory {

    private final Map<String, PayStrategy> strategyMap;

    @Autowired
    public PayStrategyFactory(Map<String, PayStrategy> strategyMap) {
        this.strategyMap = new ConcurrentHashMap<>(strategyMap);
    }

    /**
     * 根据支付类型获取对应的支付策略
     * @param payType 支付类型（如wxpay、alipay等）
     * @return 支付策略实例
     */
    public PayStrategy getStrategy(String payType) {
        PayStrategy strategy = strategyMap.get(payType + "PayStrategy");
        if (strategy == null) {
            throw new IllegalArgumentException("不支持的支付类型: " + payType);
        }
        return strategy;
    }
}