package com.menu.menu.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxPayConfiguration {

    @Value("${wx.pay.appid}")
    private String appId;

    @Value("${wx.pay.mch-id}")
    private String mchId;

    @Value("${wx.pay.mch-key}")
    private String mchKey;

    @Value("${wx.pay.notify-url}")
    private String notifyUrl;

    @Bean
    public WxPayService wxPayService() {
        WxPayConfig config = new WxPayConfig();
        config.setAppId(appId);
        config.setMchId(mchId);
        config.setMchKey(mchKey);
        config.setNotifyUrl(notifyUrl);
        // 可以根据实际情况添加证书路径等配置

        WxPayService service = new WxPayServiceImpl();
        service.setConfig(config);
        return service;
    }
}