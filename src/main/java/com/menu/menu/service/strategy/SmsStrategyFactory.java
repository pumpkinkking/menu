package com.menu.menu.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SmsStrategyFactory {

    private final Map<String, SmsStrategy> strategyMap;

    @Autowired
    public SmsStrategyFactory(Map<String, SmsStrategy> strategyMap) {
        this.strategyMap = new ConcurrentHashMap<>(strategyMap);
    }

    /**
     * 根据短信服务商类型获取对应的短信策略
     * @param smsType 短信服务商类型（如aliyun、tencent等）
     * @return 短信策略实例
     */
    public SmsStrategy getStrategy(String smsType) {
        SmsStrategy strategy = strategyMap.get(smsType + "SmsStrategy");
        if (strategy == null) {
            throw new IllegalArgumentException("不支持的短信服务商: " + smsType);
        }
        return strategy;
    }
}