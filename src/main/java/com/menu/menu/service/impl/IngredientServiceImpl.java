package com.menu.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.menu.menu.dto.IngredientDTO;
import com.menu.menu.entity.Ingredient;
import com.menu.menu.mapper.IngredientMapper;
import com.menu.menu.service.IngredientService;
import com.menu.menu.vo.IngredientVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientMapper ingredientMapper;

    @Override
    public List<IngredientVO> getIngredientsByUserId(Long userId) {
        QueryWrapper<Ingredient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Ingredient> ingredients = ingredientMapper.selectList(queryWrapper);
        return ingredients.stream().map(this::convertToVO).collect(Collectors.toList());
    }

    @Override
    public Long addIngredient(IngredientDTO ingredientDTO, Long userId) {
        Ingredient ingredient = new Ingredient();
        BeanUtils.copyProperties(ingredientDTO, ingredient);
        ingredient.setUserId(userId);
        ingredientMapper.insert(ingredient);
        return ingredient.getIngredientId();
    }

    @Override
    public boolean deleteIngredient(Long id, Long userId) {
        QueryWrapper<Ingredient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("user_id", userId);
        int rows = ingredientMapper.delete(queryWrapper);
        return rows > 0;
    }

    private IngredientVO convertToVO(Ingredient ingredient) {
        IngredientVO vo = new IngredientVO();
        BeanUtils.copyProperties(ingredient, vo);
        return vo;
    }
}