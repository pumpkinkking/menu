package com.menu.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;

/**
 * 订单详情实体类
 * 对应数据库表order_detail，存储订单中的食材明细
 */
@TableName("order_detail")
public class OrderDetail {
    /**
     * 订单ID
     * 关联order表的主键
     */
    @TableId(value = "order_id", type = IdType.INPUT)
    private Integer orderId;

    /**
     * 食材ID
     * 关联ingredient表的主键
     */
    @TableId(value = "ingredient_id", type = IdType.INPUT)
    private Integer ingredientId;

    /**
     * 食材名称
     */
    @TableField("ingredient_name")
    private String ingredientName;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 计量单位
     */
    private String unit;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 小计金额
     */
    @TableField("subtotal_price")
    private BigDecimal subtotalPrice;

    // Getters and setters
    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public Integer getIngredientId() { return ingredientId; }
    public void setIngredientId(Integer ingredientId) { this.ingredientId = ingredientId; }
    public String getIngredientName() { return ingredientName; }
    public void setIngredientName(String ingredientName) { this.ingredientName = ingredientName; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public BigDecimal getSubtotalPrice() { return subtotalPrice; }
    public void setSubtotalPrice(BigDecimal subtotalPrice) { this.subtotalPrice = subtotalPrice; }
}