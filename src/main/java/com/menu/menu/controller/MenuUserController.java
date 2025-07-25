package com.menu.menu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.menu.menu.entity.MenuUser;
import com.menu.menu.service.MenuUserService;
import com.menu.menu.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 用户菜单关联表控制器
 * 提供用户菜单关联关系及价格信息的CRUD操作接口
 */
@RestController
@RequestMapping("/menuUser")
public class MenuUserController {

    @Autowired
    private MenuUserService menuUserService;

    /**
     * 保存或更新用户菜单关联关系
     * @param menuUser 关联关系实体
     * @return 操作结果
     */
    @PostMapping
    public Result save(@RequestBody MenuUser menuUser) {
        // 设置创建者ID（实际项目中应从当前登录用户获取）
        menuUser.setCreateUser(1L);
        boolean success = menuUserService.saveOrUpdate(menuUser);
        return success ? Result.success() : Result.error("保存失败");
    }

    /**
     * 根据用户ID查询关联的菜单列表
     * @param userId 用户ID
     * @return 菜单关联列表
     */
    @GetMapping("/user/{userId}")
    public Result<List<MenuUser>> getByUserId(@PathVariable Long userId) {
        List<MenuUser> menuUserList = menuUserService.getByUserId(userId);
        return Result.success(menuUserList);
    }

    /**
     * 根据菜单ID和用户ID查询价格
     * @param menuId 菜单ID
     * @param userId 用户ID
     * @return 价格信息
     */
    @GetMapping("/price")
    public Result<BigDecimal> getPrice(
            @RequestParam Long menuId,
            @RequestParam Long userId) {
        BigDecimal price = menuUserService.getPriceByMenuIdAndUserId(menuId, userId);
        return Result.success(price);
    }

    /**
     * 根据菜单ID和用户ID删除关联关系
     * @param menuId 菜单ID
     * @param userId 用户ID
     * @return 操作结果
     */
    @DeleteMapping
    public Result delete(
            @RequestParam Long menuId,
            @RequestParam Long userId) {
        QueryWrapper<MenuUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_id", menuId)
                   .eq("user_id", userId);
        boolean success = menuUserService.remove(queryWrapper);
        return success ? Result.success() : Result.error("删除失败");
    }
}