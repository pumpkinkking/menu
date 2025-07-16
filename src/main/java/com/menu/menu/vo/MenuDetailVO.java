package com.menu.menu.vo;

import com.menu.menu.entity.MenuIngredient;
import com.menu.menu.entity.MenuStep;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MenuDetailVO {
    private Integer id;
    private String name;
    private String description;
    private String coverImageUrl;
    private Integer categoryId;
    private String userId;
    private Integer viewCount;
    private Integer collectCount;
    private Integer shareCount;
    private LocalDateTime createTime;
    private List<MenuIngredient> ingredients;
    private List<MenuStep> steps;
    private boolean isCollected;
}