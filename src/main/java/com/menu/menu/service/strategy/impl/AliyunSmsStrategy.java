package com.menu.menu.service.strategy.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.menu.menu.service.strategy.SmsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component("aliyunSmsStrategy")
public class AliyunSmsStrategy implements SmsStrategy {

    @Value("${aliyun.sms.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.sms.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.sign-name}")
    private String signName;

    @Value("${aliyun.sms.template-code}")
    private String templateCode;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String SMS_CODE_KEY = "sms:code:%s";
    private static final int CODE_EXPIRE_MINUTES = 5;

    @Override
    @Async("taskExecutor") // 使用配置的线程池
    public CompletableFuture<String> sendVerificationCode(String phone) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String code = generateRandomCode(6);
                Client client = createClient(accessKeyId, accessKeySecret);
                SendSmsRequest request = new SendSmsRequest()
                        .setPhoneNumbers(phone)
                        .setSignName(signName)
                        .setTemplateCode(templateCode)
                        .setTemplateParam("{\"code\":\"" + code + "\"}");

                SendSmsResponse response = client.sendSms(request);
                if ("OK".equals(response.getBody().getCode())) {
                    redisTemplate.opsForValue().set(
                            String.format(SMS_CODE_KEY, phone),
                            code,
                            CODE_EXPIRE_MINUTES,
                            TimeUnit.MINUTES
                    );
                    return code;
                } else {
                    throw new RuntimeException("短信发送失败: " + response.getBody().getMessage());
                }
            } catch (Exception e) {
                throw new RuntimeException("短信服务异常: " + e.getMessage());
            }
        });
    }

    @Override
    public boolean verifyCode(String phone, String code) {
        String key = String.format(SMS_CODE_KEY, phone);
        String storedCode = redisTemplate.opsForValue().get(key);

        if (storedCode == null) {
            return false;
        }

        boolean isMatch = storedCode.equals(code);
        if (isMatch) {
            redisTemplate.delete(key);
        }
        return isMatch;
    }

    private String generateRandomCode(int length) {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            codeBuilder.append(random.nextInt(10));
        }
        return codeBuilder.toString();
    }

    private Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }
}