package com.menu.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("basket")
public class Basket {

    @TableId(value = "basket_id", type = IdType.AUTO)
    private Integer basketId;

    @TableField("user_id")
    private String userId;

    @TableField("ingredient_id")
    private Integer ingredientId;

    private Integer quantity;

    private String unit;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

}