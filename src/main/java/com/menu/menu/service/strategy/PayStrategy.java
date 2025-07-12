package com.menu.menu.service.strategy;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;

public interface PayStrategy {
    /**
     * 创建支付订单
     * @param orderNo 订单编号
     * @param totalFee 订单金额(分)
     * @param openid 用户openid
     * @param body 订单描述
     * @return 支付参数
     * @throws WxPayException 支付异常
     */
    WxPayMpOrderResult createOrder(String orderNo, Integer totalFee, String openid, String body) throws WxPayException;

    /**
     * 处理支付回调
     * @param xmlData 回调数据
     * @return 回调响应
     * @throws WxPayException 支付异常
     */
    String handlePayNotify(String xmlData) throws WxPayException;
}