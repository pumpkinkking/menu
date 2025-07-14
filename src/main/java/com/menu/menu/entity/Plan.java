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
import java.util.Date;
import java.util.List;

/**
 * 计划实体类
 */
@Data
@TableName("plan")
public class Plan {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NotBlank(message = "计划标题不能为空")
    private String title;

    @NotNull(message = "计划日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    @NotEmpty(message = "餐食列表不能为空")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> meals;
    private Date createTime;
    private Date updateTime;
}