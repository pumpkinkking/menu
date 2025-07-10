package com.couple.menu.vo;

import com.couple.menu.entity.MenuIngredient;
import com.couple.menu.entity.MenuStep;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MenuDetailVO {
    private Long id;
    private String name;
    private String description;
    private String coverImageUrl;
    private Long categoryId;
    private Long userId;
    private Integer viewCount;
    private Integer collectCount;
    private Integer shareCount;
    private LocalDateTime createTime;
    private List<MenuIngredient> ingredients;
    private List<MenuStep> steps;
    private boolean isCollected;
}