package com.menu.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 餐单实体类
 * 对应数据库表menu，存储餐单的基本信息
 */
@Data
@TableName("menu")
public class Menu {
    /**
     * 餐单ID
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 餐单名称
     */
    private String name;
    
    /**
     * 餐单描述
     */
    private String description;
    
    /**
     * 封面图片URL
     */
    @TableField("cover_image_url")
    private String coverImageUrl;

    /**
     * 封面缩略图URL
     */
    @TableField("thumbnail_url")
    private String thumbnailUrl;
    
    /**
     * 分类ID
     * 关联分类表的主键
     */
    @TableField("category_id")
    private Long categoryId;
    
    /**
     * 用户ID
     * 创建者ID，关联用户表的主键
     */
    @TableField("user_id")
    private Long userId;
    
    /**
     * 查看次数
     */
    @TableField("view_count")
    private Integer viewCount;
    
    /**
     * 收藏次数
     */
    @TableField("collect_count")
    private Integer collectCount;
    
    /**
     * 分享次数
     */
    @TableField("share_count")
    private Integer shareCount;

    /**
     * 获取分享次数
     */
    public Integer getShareCount() {
        return shareCount;
    }
    
    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
    
    /**
     * 删除标志
     * 0-未删除，1-已删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;

    /**
     * 手动添加的setter方法，用于测试Lombok是否正常工作
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}