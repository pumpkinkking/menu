package com.menu.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.menu.menu.entity.MenuCollection;
import org.apache.ibatis.annotations.Param;

public interface MenuCollectionMapper extends BaseMapper<MenuCollection> {
    int deleteByUserIdAndMenuId(@Param("userId") String userId, @Param("menuId") Integer menuId);
    MenuCollection selectByUserIdAndMenuId(@Param("userId") String userId, @Param("menuId") Integer menuId);
}