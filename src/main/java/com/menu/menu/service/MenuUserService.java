package com.menu.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.menu.menu.entity.MenuUser;
import java.math.BigDecimal;
import java.util.List;

/**
 * 用户菜单关联表服务接口
 * 提供用户菜单关联关系的管理和价格查询等业务逻辑
 */
public interface MenuUserService extends IService<MenuUser> {
    /**
     * 根据用户ID查询关联的菜单列表
     * @param userId 用户ID
     * @return 菜单关联列表
     */
    List<MenuUser> getByUserId(Long userId);

    /**
     * 根据菜单ID和用户ID查询价格
     * @param menuId 菜单ID
     * @param userId 用户ID
     * @return 价格信息
     */
    BigDecimal getPriceByMenuIdAndUserId(Long menuId, Long userId);
}