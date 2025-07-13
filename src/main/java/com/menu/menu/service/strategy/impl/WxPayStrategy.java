package com.menu.menu.service.strategy.impl;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.menu.menu.service.strategy.PayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("wxPayStrategy")
public class WxPayStrategy implements PayStrategy {

    @Autowired
    private WxPayService wxPayService;

    @Value("${wx.pay.notify-url}")
    private String notifyUrl;

    @Override
    public WxPayMpOrderResult createOrder(String orderNo, Integer totalFee, String openid, String body) throws WxPayException {
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setOutTradeNo(orderNo);
        request.setTotalFee(totalFee);
        request.setOpenid(openid);
        request.setBody(body);
        request.setNotifyUrl(notifyUrl);
        request.setTradeType("JSAPI");
        request.setSpbillCreateIp("127.0.0.1");

        return wxPayService.createOrder(request);
    }

    @Override
    public String handlePayNotify(String xmlData) throws WxPayException {
        WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlData);
        // 订单状态更新逻辑
        String outTradeNo = result.getOutTradeNo();
        String transactionId = result.getTransactionId();
        String totalFee = String.valueOf(result.getTotalFee());

        // TODO: 更新订单状态为已支付

        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }
}