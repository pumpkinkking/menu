package com.menu.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 计划实体类
 */
@Data
@TableName("plan")
public class Plan {
    @TableId(value = "plan_id", type = IdType.AUTO)
    private Integer planId;

    @TableField("user_id")
    private String userId;

    @NotBlank(message = "计划标题不能为空")
    @TableField("plan_title")
    private String planTitle;

    @NotNull(message = "计划日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("plan_date")
    private LocalDate planDate;

    @NotEmpty(message = "餐食列表不能为空")
    @TableField(value = "meal_list", typeHandler = JacksonTypeHandler.class)
    private List<String> mealList;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}