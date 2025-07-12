package com.menu.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.menu.menu.entity.MenuIngredient;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MenuIngredientMapper extends BaseMapper<MenuIngredient> {
    List<MenuIngredient> selectByMenuId(@Param("menuId") Long menuId);
    int batchInsert(@Param("ingredients") List<MenuIngredient> ingredients);
}