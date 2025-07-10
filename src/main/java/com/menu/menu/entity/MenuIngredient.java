package com.menu.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 餐单食材实体类
 * 对应数据库表menu_ingredient，存储餐单所需的食材信息
 */
@Data
@TableName("menu_ingredient")
public class MenuIngredient {
    /**
     * 食材ID
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 餐单ID
     * 关联menu表的主键
     */
    @TableField("menu_id")
    private Long menuId;
    
    /**
     * 食材名称
     */
    private String name;
    
    /**
     * 食材数量
     */
    private String quantity;
    
    /**
     * 计量单位
     */
    private String unit;
    
    /**
     * 排序序号
     * 用于控制食材展示顺序
     */
    @TableField("sort_order")
    private Integer sortOrder;
}