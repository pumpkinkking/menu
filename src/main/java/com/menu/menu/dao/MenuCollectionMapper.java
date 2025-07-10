package com.couple.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.couple.menu.entity.MenuCollection;
import org.apache.ibatis.annotations.Param;

public interface MenuCollectionMapper extends BaseMapper<MenuCollection> {
    int deleteByUserIdAndMenuId(@Param("userId") Long userId, @Param("menuId") Long menuId);
    MenuCollection selectByUserIdAndMenuId(@Param("userId") Long userId, @Param("menuId") Long menuId);
}