package com.menu.menu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 计划视图对象
 * 用于封装根据用户ID和日期查询的计划详情结果
 */
@Data
public class PlanVO {
    /**
     * 菜单类型
     */
    @TableField("menu_type")
    private Integer menuType;

    /**
     * 顺序
     */
    @TableField("order")
    private Integer sortOrder;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 食材列表
     * 多个食材用逗号分隔
     */
    @TableField("ingredients")
    private String ingredients;
}