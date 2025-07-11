package com.menu.menu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.menu.menu.entity.MenuCollection;
import org.apache.ibatis.annotations.Param;

public interface MenuCollectionMapper extends BaseMapper<MenuCollection> {
    int deleteByUserIdAndMenuId(@Param("userId") Long userId, @Param("menuId") Long menuId);
    MenuCollection selectByUserIdAndMenuId(@Param("userId") Long userId, @Param("menuId") Long menuId);
}