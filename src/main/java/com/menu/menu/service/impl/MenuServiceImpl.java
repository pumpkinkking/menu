package com.menu.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.menu.menu.entity.*;
import com.menu.menu.mapper.*;
import com.menu.menu.service.MenuService;
import com.menu.menu.vo.MenuDetailVO;
import com.menu.menu.vo.MenuVO;
import com.menu.menu.dto.MenuDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 餐单服务实现类
 * 实现MenuService接口定义的业务逻辑，处理餐单的创建、查询、收藏、分享等功能
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuIngredientMapper menuIngredientMapper;

    @Autowired
    private MenuStepMapper menuStepMapper;

    @Autowired
    private MenuCollectionMapper menuCollectionMapper;

    @Autowired
    private MenuShareMapper menuShareMapper;

    /**
     * 上传餐单
     * 事务性操作，保存餐单基本信息、食材和步骤
     * @param menuDTO 餐单数据传输对象，包含基本信息、食材列表和步骤列表
     * @param userId 创建者用户ID
     * @return Integer 新创建餐单的ID
     */
    @Override
    @Transactional
    public Integer uploadMenu(MenuDTO menuDTO, String userId) {
        // 保存餐单基本信息
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDTO, menu);
        menu.setUserId(userId);
        menu.setCoverImageUrl(menuDTO.getCoverImageUrl());
        menu.setThumbnailUrl(menuDTO.getThumbnailUrl());
        menu.setViewCount(0);
        menu.setCollectCount(0);
        menu.setShareCount(0);
        menu.setCreateTime(LocalDateTime.now());
        menu.setUpdateTime(LocalDateTime.now());
        menu.setIsDeleted(0);
        menuMapper.insert(menu);

        // 保存食材信息
        if (menuDTO.getIngredients() != null && !menuDTO.getIngredients().isEmpty()) {
            List<MenuIngredient> ingredients = menuDTO.getIngredients().stream().map(ingredientDTO -> {
                MenuIngredient ingredient = new MenuIngredient();
                BeanUtils.copyProperties(ingredientDTO, ingredient);
                ingredient.setMenuId(menu.getMenuId());
                return ingredient;
            }).collect(Collectors.toList());
            menuIngredientMapper.batchInsert(ingredients);
        }

        // 保存步骤信息
        if (menuDTO.getSteps() != null && !menuDTO.getSteps().isEmpty()) {
            List<MenuStep> steps = menuDTO.getSteps().stream().map(stepDTO -> {
                MenuStep step = new MenuStep();
                BeanUtils.copyProperties(stepDTO, step);
                // 将旧方法调用替换为实体类规范方法
                step.setMenuId(menu.getMenuId());  // 原方法getId()已弃用
                return step;
            }).collect(Collectors.toList());
            menuStepMapper.batchInsert(steps);
        }

        return menu.getMenuId();
    }

    /**
     * 批量上传餐单
     * 循环调用uploadMenu方法处理多个餐单上传
     * @param menuDTOs 餐单数据传输对象列表
     * @param userId 创建者用户ID
     * @return List<Long> 新创建餐单的ID列表
     */
    @Override
    @Transactional
    public List<Integer> batchUploadMenus(List<MenuDTO> menuDTOs, String userId) {
        List<Integer> menuIds = new ArrayList<>();
        for (MenuDTO menuDTO : menuDTOs) {
            Integer menuId = uploadMenu(menuDTO, userId);
            menuIds.add(menuId);
        }
        return menuIds;
    }

    /**
     * 按分类浏览餐单
     * @param categoryId 分类ID
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return IPage<MenuVO> 分页的餐单视图对象列表
     */
    @Override
    public IPage<MenuVO> getMenusByCategory(Integer categoryId, int pageNum, int pageSize) {
        Page<Menu> page = new Page<>(pageNum, pageSize);
        IPage<Menu> menuPage = menuMapper.selectByCategory(page, categoryId);
        return convertToMenuVOPage(menuPage);
    }

    /**
     * 按热门程度浏览餐单
     * 按浏览次数降序排列
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return IPage<MenuVO> 分页的热门餐单视图对象列表
     */
    @Override
    public IPage<MenuVO> getHotMenus(int pageNum, int pageSize) {
        Page<Menu> page = new Page<>(pageNum, pageSize);
        IPage<Menu> menuPage = menuMapper.selectByHot(page);
        return convertToMenuVOPage(menuPage);
    }

    /**
     * 按最新上传浏览餐单
     * 按创建时间降序排列
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return IPage<MenuVO> 分页的最新餐单视图对象列表
     */
    @Override
    public IPage<MenuVO> getNewestMenus(int pageNum, int pageSize) {
        Page<Menu> page = new Page<>(pageNum, pageSize);
        IPage<Menu> menuPage = menuMapper.selectByNewest(page);
        return convertToMenuVOPage(menuPage);
    }

    /**
     * 搜索餐单
     * 根据餐单名称和描述模糊搜索
     * @param keyword 搜索关键词
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return IPage<MenuVO> 分页的搜索结果餐单视图对象列表
     */
    @Override
    public IPage<MenuVO> searchMenus(String keyword, int pageNum, int pageSize) {
        Page<Menu> page = new Page<>(pageNum, pageSize);
        IPage<Menu> menuPage = menuMapper.search(page, keyword);
        return convertToMenuVOPage(menuPage);
    }

    /**
     * 获取餐单详情
     * 包含基本信息、食材列表、步骤列表，并更新浏览次数
     * @param menuId 餐单ID
     * @param userId 用户ID（可为null，未登录用户）
     * @return MenuDetailVO 餐单详情视图对象，包含收藏状态
     */
    @Override
    public MenuDetailVO getMenuDetail(Integer menuId, String userId) {
        // 更新浏览次数
        Menu menu = menuMapper.selectById(menuId);
        if (menu == null || menu.getIsDeleted() == 1) {
            return null;
        }
        menu.setViewCount(menu.getViewCount() + 1);
        menuMapper.updateById(menu);

        // 构建详情VO
        MenuDetailVO detailVO = new MenuDetailVO();
        BeanUtils.copyProperties(menu, detailVO);

        // 查询食材
        List<MenuIngredient> ingredients = menuIngredientMapper.selectByMenuId(menuId);
        detailVO.setIngredients(ingredients);

        // 查询步骤
        List<MenuStep> steps = menuStepMapper.selectByMenuId(menuId);
        detailVO.setSteps(steps);

        // 查询是否收藏
        if (userId != null) {
            MenuCollection collection = menuCollectionMapper.selectByUserIdAndMenuId(userId, menuId);
            detailVO.setCollected(collection != null);
        }

        return detailVO;
    }

    /**
     * 收藏餐单
     * 新增收藏记录并更新餐单收藏次数
     * @param menuId 餐单ID
     * @param userId 用户ID
     * @return boolean 收藏是否成功（已收藏返回true）
     */
    @Override
    @Transactional
    public boolean collectMenu(Integer menuId, String userId) {
        // 检查是否已收藏
        MenuCollection existing = menuCollectionMapper.selectByUserIdAndMenuId(userId, menuId);
        if (existing != null) {
            return true; // 已收藏
        }

        // 新增收藏记录
        MenuCollection collection = new MenuCollection();
        collection.setUserId(userId);
        collection.setMenuId(menuId);
        collection.setCreateTime(LocalDateTime.now());
        menuCollectionMapper.insert(collection);

        // 更新餐单收藏次数
        Menu menu = menuMapper.selectById(menuId);
        menu.setCollectCount(menu.getCollectCount() + 1);
        menuMapper.updateById(menu);

        return true;
    }


    /**
     * 取消收藏餐单
     * 删除收藏记录并更新餐单收藏次数
     * @param menuId 餐单ID
     * @param userId 用户ID
     * @return boolean 取消收藏是否成功（未收藏返回false）
     */
    @Override
    @Transactional
    public boolean cancelCollectMenu(Integer menuId, String userId) {
        // 删除收藏记录
        int rows = menuCollectionMapper.deleteByUserIdAndMenuId(userId, menuId);
        if (rows <= 0) {
            return false; // 未收藏
        }

        // 更新餐单收藏次数
        Menu menu = menuMapper.selectById(menuId);
        menu.setCollectCount(menu.getCollectCount() - 1);
        menuMapper.updateById(menu);

        return true;
    }


    /**
     * 获取用户收藏的餐单
     * 查询用户收藏的所有餐单并分页返回
     * @param userId 用户ID
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return IPage<MenuVO> 分页的用户收藏餐单视图对象列表
     */
    @Override
    public IPage<MenuVO> getUserCollectedMenus(String userId, int pageNum, int pageSize) {
        Page<Menu> page = new Page<>(pageNum, pageSize);
        List<Menu> menus = menuMapper.selectCollectedByUserId(userId);
        Page<Menu> menuPage = new Page<>(pageNum, pageSize);
        menuPage.setRecords(menus);
        menuPage.setTotal(menus.size());
        return convertToMenuVOPage(menuPage);
    }


    /**
     * 分享餐单
     * 记录分享记录并更新餐单分享次数
     * @param menuId 餐单ID
     * @param userId 用户ID
     * @param platform 分享平台
     * @return boolean 分享是否成功
     */
    @Override
    @Transactional
    public boolean shareMenu(Integer menuId, String userId, String platform) {
        // 检查餐单是否存在
        Menu menu = menuMapper.selectById(menuId);
        if (menu == null || menu.getIsDeleted() == 1) {
            return false;
        }

        // 记录分享记录
        MenuShare share = new MenuShare();
        share.setMenuId(menuId);
        share.setUserId(userId);
        share.setCreateTime(LocalDateTime.now());
        menuShareMapper.insert(share);

        // 更新餐单分享次数
        menu.setShareCount(menu.getShareCount() + 1);
        menuMapper.updateById(menu);

        return true;
    }

    /**
     * 获取用户创建的餐单
     * 查询用户创建的所有餐单并分页返回
     * @param userId 用户ID
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return IPage<MenuVO> 分页的用户创建餐单视图对象列表
     */
    @Override
    public IPage<MenuVO> getUserCreatedMenus(String userId, int pageNum, int pageSize) {
        Page<Menu> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                    .eq("is_deleted", 0)
                    .orderByDesc("create_time");
        
        IPage<Menu> menuPage = menuMapper.selectPage(page, queryWrapper);
        return convertToMenuVOPage(menuPage);
    }

    /**
     * 将Menu分页对象转换为MenuVO分页对象
     * @param menuPage Menu分页对象
     * @return IPage<MenuVO> MenuVO分页对象
     */
    private IPage<MenuVO> convertToMenuVOPage(IPage<Menu> menuPage) {
        return menuPage.convert(menu -> {
            MenuVO vo = new MenuVO();
            BeanUtils.copyProperties(menu, vo);
            return vo;
        });
    }
}