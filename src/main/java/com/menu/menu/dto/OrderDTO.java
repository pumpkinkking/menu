package com.menu.menu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "订单数据传输对象")
public class OrderDTO {

    @Schema(description = "订单日期", required = true)
    @NotEmpty(message = "订单日期不能为空")
    private String date;

    @Schema(description = "菜品列表", required = true)
    @NotEmpty(message = "菜品列表不能为空")
    private List<OrderDishDTO> dishes;

    @Schema(description = "订单总价", required = true)
    @NotNull(message = "订单总价不能为空")
    private BigDecimal totalPrice;

    // Getters and setters
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public List<OrderDishDTO> getDishes() { return dishes; }
    public void setDishes(List<OrderDishDTO> dishes) { this.dishes = dishes; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    @Schema(description = "订单项数据传输对象")
    public static class OrderDishDTO {
        @Schema(description = "菜品ID", required = true)
        @NotNull(message = "菜品ID不能为空")
        private Long dishId;

        @Schema(description = "菜品数量", required = true)
        @NotNull(message = "菜品数量不能为空")
        private Integer quantity;

        // Getters and setters
        public Long getDishId() { return dishId; }
        public void setDishId(Long dishId) { this.dishId = dishId; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }
}