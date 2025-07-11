package com.menu.menu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.menu.menu.entity.Ingredient;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IngredientMapper extends BaseMapper<Ingredient> {
}