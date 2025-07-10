package com.menu.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 餐单收藏实体类
 * 对应数据库表menu_collection，存储用户收藏餐单的记录
 */
@Data
@TableName("menu_collection")
public class MenuCollection {
    /**
     * 收藏ID
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 用户ID
     * 收藏者ID，关联用户表的主键
     */
    @TableField("user_id")
    private Long userId;
    
    /**
     * 餐单ID
     * 被收藏餐单的ID，关联menu表的主键
     */
    @TableField("menu_id")
    private Long menuId;
    
    /**
     * 收藏时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}