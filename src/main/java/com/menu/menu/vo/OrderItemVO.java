package com.menu.menu.vo;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 订单项视图对象，用于展示订单中的菜品信息
 */
@Data
public class OrderItemVO {
    /**
     * 订单项ID
     */
    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 菜品ID
     */
    private Integer dishId;

    /**
     * 菜品名称
     */
    private String dishName;

    /**
     * 菜品图片
     */
    private String dishImage;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 小计金额
     */
    private BigDecimal subtotal;
}