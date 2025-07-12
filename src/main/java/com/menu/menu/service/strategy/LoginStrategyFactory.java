package com.menu.menu.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginStrategyFactory {

    private final Map<String, LoginStrategy> strategyMap;

    @Autowired
    public LoginStrategyFactory(List<LoginStrategy> strategyList) {
        this.strategyMap = new ConcurrentHashMap<>();
        // 将所有登录策略注册到Map中
        strategyList.forEach(strategy -> strategyMap.put(strategy.getLoginType(), strategy));
    }

    /**
     * 根据登录类型获取对应的登录策略
     * @param loginType 登录类型（如wechat、phone等）
     * @return 登录策略实例
     */
    public LoginStrategy getStrategy(String loginType) {
        LoginStrategy strategy = strategyMap.get(loginType);
        if (strategy == null) {
            throw new IllegalArgumentException("不支持的登录类型: " + loginType);
        }
        return strategy;
    }
}