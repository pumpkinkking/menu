package com.menu.menu.service;

import com.menu.menu.dto.IngredientDTO;
import com.menu.menu.vo.IngredientVO;
import java.util.List;

public interface IngredientService {
    List<IngredientVO> getIngredientsByUserId(String userId);
    Integer addIngredient(IngredientDTO ingredientDTO, String userId);
    boolean deleteIngredient(Integer id, String userId);
}