package com.menu.menu.controller;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.menu.menu.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    /**
     * 创建支付订单
     */
    @PostMapping("/createOrder")
    public WxPayMpOrderResult createOrder(
            @RequestParam String orderNo,
            @RequestParam Integer totalFee,
            @RequestParam String openid,
            @RequestParam String body) throws WxPayException {
        return payService.createOrder(orderNo, totalFee, openid, body);
    }

    /**
     * 支付回调通知
     */
    @PostMapping("/notify")
    public String handlePayNotify(@RequestBody String xmlData) throws WxPayException {
        return payService.handlePayNotify(xmlData);
    }
}