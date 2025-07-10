package com.couple.menu.dto;

import com.couple.menu.entity.MenuIngredient;
import com.couple.menu.entity.MenuStep;
import lombok.Data;
import java.util.List;

@Data
public class MenuDTO {
    private String name;
    private String description;
    private String coverImageUrl;
    private Long categoryId;
    private List<MenuIngredient> ingredients;
    private List<MenuStep> steps;
}