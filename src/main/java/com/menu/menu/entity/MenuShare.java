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
     * 餐单分享ID
     * 被分享餐单的ID，关联menu表的主键
     */
    @TableField("menu_share_id")
    private Integer menuShareId;

    /**
     * 餐单ID
     * 被分享餐单的ID，关联menu表的主键
     */
    @TableField("menu_id")
    private Integer menuId;
    
    /**
     * 用户ID
     * 分享者ID，关联用户表的主键
     */
    @TableField("user_id")
    private String userId;
    
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

    /**
     * 手动添加的setter方法，解决Lombok可能未生成的问题
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}