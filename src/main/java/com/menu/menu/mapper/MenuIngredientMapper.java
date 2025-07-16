package com.menu.menu.mapper;

import com.menu.menu.entity.MenuIngredient;
import java.util.List;

public interface MenuIngredientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuIngredient row);

    MenuIngredient selectByPrimaryKey(Integer id);

    List<MenuIngredient> selectAll();

    int updateByPrimaryKey(MenuIngredient row);

    void batchInsert(List<MenuIngredient> ingredients);

    List<MenuIngredient> selectByMenuId(Integer menuId);
}