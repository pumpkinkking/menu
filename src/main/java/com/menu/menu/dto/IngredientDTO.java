package com.menu.menu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "食材数据传输对象")
public class IngredientDTO {

    @Schema(description = "食材名称", required = true)
    @NotBlank(message = "食材名称不能为空")
    private String name;

    @Schema(description = "食材数量", required = true)
    @NotNull(message = "食材数量不能为空")
    private Integer quantity;

    @Schema(description = "计量单位", required = true)
    @NotBlank(message = "计量单位不能为空")
    private String unit;

    @Schema(description = "存储位置")
    private String location;

    @Schema(description = "新鲜度")
    private String freshness;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getFreshness() { return freshness; }
    public void setFreshness(String freshness) { this.freshness = freshness; }
}