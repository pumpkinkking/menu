package com.menu.menu.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "食材视图对象")
public class IngredientVO {

    @Schema(description = "食材ID")
    private Integer id;

    @Schema(description = "食材名称")
    private String name;

    @Schema(description = "食材数量")
    private Integer quantity;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "存储位置")
    private String location;

    @Schema(description = "新鲜度")
    private String freshness;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}