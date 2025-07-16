package com.menu.menu.controller;

import com.menu.menu.service.SmsService;
import com.menu.menu.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    /**
     * 发送手机验证码
     */
    @PostMapping("/sendCode")
    public CompletableFuture<Result<String>> sendCode(@RequestParam String phone) {
        return smsService.sendVerificationCode(phone)
                .thenApply(code -> Result.success(code))
                .exceptionally(e -> Result.error(e.getMessage()));
    }

    /**
     * 验证手机验证码
     */
    @PostMapping("/verifyCode")
    public boolean verifyCode(
            @RequestParam String phone,
            @RequestParam String code) {
        return smsService.verifyCode(phone, code);
    }
}