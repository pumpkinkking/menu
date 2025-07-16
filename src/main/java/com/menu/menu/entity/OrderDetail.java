package com.menu.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单详情实体类
 * 对应数据库表order_detail，存储订单中的食材明细
 */
@Data
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

}