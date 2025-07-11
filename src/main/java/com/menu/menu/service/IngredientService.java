package com.menu.menu.service;

import com.menu.menu.dto.IngredientDTO;
import com.menu.menu.vo.IngredientVO;
import java.util.List;

public interface IngredientService {
    List<IngredientVO> getIngredientsByUserId(Long userId);
    Long addIngredient(IngredientDTO ingredientDTO, Long userId);
    boolean deleteIngredient(Long id, Long userId);
}