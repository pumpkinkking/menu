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

    /**
     * 根据用户ID获取食材列表
     * @param userId 用户ID
     * @return 食材视图对象列表
     */
    @Override
    public List<IngredientVO> getIngredientsByUserId(String userId) {
        QueryWrapper<Ingredient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Ingredient> ingredients = ingredientMapper.selectList(queryWrapper);
        return ingredients.stream().map(this::convertToVO).collect(Collectors.toList());
    }

    /**
     * 添加食材
     * @param ingredientDTO 食材数据传输对象
     * @param userId 用户ID
     * @return 新增食材ID
     */
    @Override
    public Integer addIngredient(IngredientDTO ingredientDTO, String userId) {
        Ingredient ingredient = new Ingredient();
        BeanUtils.copyProperties(ingredientDTO, ingredient);
        ingredient.setUserId(userId);
        return ingredientMapper.insert(ingredient);
    }

    /**
     * 删除食材
     * @param id 食材ID
     * @param userId 用户ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteIngredient(Integer id, String userId) {
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