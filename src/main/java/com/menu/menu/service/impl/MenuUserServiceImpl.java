package com.menu.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.menu.menu.entity.MenuUser;
import com.menu.menu.mapper.MenuUserMapper;
import com.menu.menu.service.MenuUserService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

/**
 * 用户菜单关联表服务实现类
 * 实现用户菜单关联关系的业务逻辑处理
 */
@Service
public class MenuUserServiceImpl extends ServiceImpl<MenuUserMapper, MenuUser> implements MenuUserService {

    /**
     * 根据用户ID查询关联的菜单列表
     * @param userId 用户ID
     * @return 菜单关联列表
     */
    @Override
    public List<MenuUser> getByUserId(Long userId) {
        QueryWrapper<MenuUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 根据菜单ID和用户ID查询价格
     * @param menuId 菜单ID
     * @param userId 用户ID
     * @return 价格信息
     */
    @Override
    public BigDecimal getPriceByMenuIdAndUserId(Long menuId, Long userId) {
        QueryWrapper<MenuUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_id", menuId)
                   .eq("user_id", userId)
                   .select("price");
                    
        MenuUser menuUser = baseMapper.selectOne(queryWrapper);
        return menuUser != null ? menuUser.getPrice() : BigDecimal.ZERO;
    }
}