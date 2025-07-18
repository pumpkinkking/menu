package com.menu.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 餐单步骤实体类
 * 对应数据库表menu_step，存储餐单的烹饪步骤信息
 */
@Data
@TableName("menu_step")
public class MenuStep {

    /**
     * 步骤序号
     * 步骤的顺序编号
     */
    @TableId(value = "menu_step_id", type = IdType.INPUT)
    private Integer stepId;

    /**
     * 餐单ID
     */
    @TableField("menu_id")
    private Integer menuId;

    /**
     * 步骤描述
     * 烹饪步骤的文字描述
     */
    @TableField("content")
    private String content;
    
    /**
     * 步骤图片URL
     * 步骤对应的图片地址
     */
    @TableField("image_url")
    private String imageUrl;
    
    /**
     * 排序序号
     * 用于控制步骤展示顺序
     */
    @TableField("sort_order")
    private Integer sortOrder;
}