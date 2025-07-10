package com.couple.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.couple.menu.entity.Menu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    IPage<Menu> selectByCategory(Page<Menu> page, @Param("categoryId") Long categoryId);
    IPage<Menu> selectByHot(Page<Menu> page);
    IPage<Menu> selectByNewest(Page<Menu> page);
    IPage<Menu> search(Page<Menu> page, @Param("keyword") String keyword);
    List<Menu> selectByUserId(@Param("userId") Long userId);
    List<Menu> selectCollectedByUserId(@Param("userId") Long userId);
}