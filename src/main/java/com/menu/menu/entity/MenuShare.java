package com.menu.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 餐单分享记录实体类
 * 对应数据库表menu_share，存储用户分享餐单的记录
 */
@Data
@TableName("menu_share")
public class MenuShare {
    /**
     * 分享记录ID
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 餐单ID
     * 被分享餐单的ID，关联menu表的主键
     */
    @TableField("menu_id")
    private Long menuId;
    
    /**
     * 用户ID
     * 分享者ID，关联用户表的主键
     */
    @TableField("user_id")
    private Long userId;
    
    /**
     * 分享渠道
     * 如：wechat_friend(微信好友), wechat_moments(朋友圈)
     */
    @TableField("share_channel")
    private String shareChannel;
    
    /**
     * 分享时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}