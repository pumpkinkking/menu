package com.menu.menu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "食材数据传输对象")
public class IngredientDTO {

    @Schema(description = "食材名称", required = true)
    @NotBlank(message = "食材名称不能为空")
    private String name;

    @Schema(description = "食材数量", required = true)
    @NotNull(message = "食材数量不能为空")
    private Integer quantity;

    @Schema(description = "计量单位", required = true)
    @NotBlank(message = "计量单位不能为空")
    private String unit;

    @Schema(description = "存储位置")
    private String location;

    @Schema(description = "新鲜度")
    private String freshness;
}