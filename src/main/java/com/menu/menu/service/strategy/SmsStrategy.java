package com.menu.menu.service.strategy;

public interface SmsStrategy {
    /**
     * 发送手机验证码
     * @param phone 手机号
     * @return 验证码
     */
    java.util.concurrent.CompletableFuture<String> sendVerificationCode(String phone);

    /**
     * 验证手机验证码
     * @param phone 手机号
     * @param code 验证码
     * @return 是否验证成功
     */
    boolean verifyCode(String phone, String code);
}