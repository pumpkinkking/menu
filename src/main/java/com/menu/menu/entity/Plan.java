package com.menu.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 计划表实体类
 * 对应数据库表plan
 */
@Data
@TableName("plan")
public class Plan {
    /**
     * 计划ID
     */
    @TableId(value = "plan_id", type = IdType.AUTO)
    private Integer planId;

    /**
     * 计划日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("plan_date")
    private LocalDate planDate;

    /**
     * 菜品ID
     */
    @TableField("menu_id")
    private Integer menuId;

    /**
     * 餐食类型
     * 0:早餐 1:中餐 2:午餐
     */
    @TableField("meal_type")
    private Integer mealType;

    /**
     * 顺序
     */
    @TableField("order")
    private Integer order;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("update_time")
    private LocalDateTime updateTime;

}