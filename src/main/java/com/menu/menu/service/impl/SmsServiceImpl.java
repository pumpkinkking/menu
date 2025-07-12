package com.menu.menu.service.impl;

import com.menu.menu.service.SmsService;
import com.menu.menu.service.strategy.SmsStrategy;
import com.menu.menu.service.strategy.SmsStrategyFactory;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    private final SmsStrategyFactory smsStrategyFactory;

    @Autowired
    public SmsServiceImpl(SmsStrategyFactory smsStrategyFactory) {
        this.smsStrategyFactory = smsStrategyFactory;
    }

    @Override
    public CompletableFuture<String> sendVerificationCode(String phone) {
        // 默认使用阿里云短信策略
        SmsStrategy strategy = smsStrategyFactory.getStrategy("aliyun");
        return strategy.sendVerificationCode(phone);
    }

    @Override
    public boolean verifyCode(String phone, String code) {
        // 默认使用阿里云短信策略
        SmsStrategy strategy = smsStrategyFactory.getStrategy("aliyun");
        return strategy.verifyCode(phone, code);
    }
}