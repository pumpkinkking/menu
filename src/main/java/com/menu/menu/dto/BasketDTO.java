package com.menu.menu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "菜篮子数据传输对象")
public class BasketDTO {

    @Schema(description = "食材ID", required = true)
    @NotNull(message = "食材ID不能为空")
    private Integer ingredientId;

    @Schema(description = "食材数量", required = true)
    @NotNull(message = "食材数量不能为空")
    private Integer quantity;

    @Schema(description = "计量单位", required = true)
    private String unit;

    // Getters and setters
    public Integer getIngredientId() { return ingredientId; }
public void setIngredientId(Integer ingredientId) { this.ingredientId = ingredientId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}