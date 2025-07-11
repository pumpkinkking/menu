package com.menu.menu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.menu.menu.entity.MenuStep;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface MenuStepMapper extends BaseMapper<MenuStep> {
    List<MenuStep> selectByMenuId(@Param("menuId") Long menuId);
    int batchInsert(@Param("steps") List<MenuStep> steps);
}