package com.menu.menu.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单视图对象，用于前端展示订单信息
 */
@Data
public class OrderVO {
    /**
     * 订单ID
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单日期
     */
    private LocalDateTime orderDate;

    /**
     * 订单总金额
     */
    private BigDecimal totalPrice;

    /**
     * 订单状态（0：待支付，1：已支付，2：已取消）
     */
    private Integer status;

    /**
     * 订单包含的菜品列表
     */
    private List<OrderItemVO> orderItems;
}