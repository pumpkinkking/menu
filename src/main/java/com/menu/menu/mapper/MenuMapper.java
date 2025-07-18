package com.menu.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.menu.menu.entity.Menu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    IPage<Menu> selectByCategory(Page<Menu> page, @Param("categoryId") Integer categoryId);
    IPage<Menu> selectByHot(Page<Menu> page);
    IPage<Menu> selectByNewest(Page<Menu> page);
    IPage<Menu> search(Page<Menu> page, @Param("keyword") String keyword);
    List<Menu> selectByUserId(@Param("userId") String userId);
    List<Menu> selectCollectedByUserId(@Param("userId") String userId);
}