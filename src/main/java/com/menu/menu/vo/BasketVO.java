package com.menu.menu.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 菜篮子视图对象
 * 用于前端展示菜篮子中的食材信息
 */
@Data
public class BasketVO {
    private Long id; // 菜篮子项ID
    private Long ingredientId; // 食材ID
    private String ingredientName; // 食材名称
    private BigDecimal quantity; // 数量
    private String unit; // 计量单位
    private String storageLocation; // 存储位置
    private Integer freshness; // 新鲜度(0-100)
    private LocalDateTime createTime; // 添加时间
}