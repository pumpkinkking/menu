package com.menu.menu.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 菜单食材关联实体类
 * 用于存储菜单与食材的关联关系及用量信息
 */
@Data
@TableName("menu_ingredient")
public class MenuIngredient {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private Integer menuId;

    /**
     * 食材ID
     */
    @TableField("ingredient_id")
    private Integer ingredientId;

    /**
     * 食材名称
     */
    @TableField("ingredient_name")
    private String ingredientName;

    /**
     * 数量
     */
    private String quantity;

    /**
     * 单位
     */
    private String unit;

    /**
     * 排序顺序
     */
    @TableField("sort_order")
    private Integer sortOrder;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}