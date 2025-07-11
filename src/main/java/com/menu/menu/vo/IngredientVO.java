package com.menu.menu.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "食材视图对象")
public class IngredientVO {

    @Schema(description = "食材ID")
    private Long id;

    @Schema(description = "食材名称")
    private String name;

    @Schema(description = "食材数量")
    private Integer quantity;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "存储位置")
    private String location;

    @Schema(description = "新鲜度")
    private String freshness;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}