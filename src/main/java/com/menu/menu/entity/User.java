package com.menu.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_user")
public class User {
    @TableId(type = IdType.INPUT)
    private String userId;
    @TableField("open_id")
    private String openId;
    @TableField("phone_num")
    private String phoneNum;
    private String username;
    @TableField("avatar_url")
    private String avatarUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}