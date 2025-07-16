package com.menu.menu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.menu.menu.entity.Menu;
import com.menu.menu.vo.MenuDetailVO;
import com.menu.menu.vo.MenuVO;
import com.menu.menu.dto.MenuDTO;
import java.util.List;

/**
 * 餐单服务接口
 * 定义餐单相关的业务逻辑方法，包括餐单的CRUD、查询、收藏、分享等功能
 */
public interface MenuService extends IService<Menu> {
    /**
     * 上传餐单
     * @param menuDTO 餐单数据传输对象，包含餐单基本信息、食材和步骤
     * @param userId 创建者用户ID
     * @return Integer 新创建餐单的ID
     */
    Integer uploadMenu(MenuDTO menuDTO, String userId);
    
    /**
     * 批量上传餐单
     * @param menuDTOs 餐单数据传输对象列表
     * @param userId 创建者用户ID
     * @return List<Integer> 新创建餐单的ID列表
     */
    List<Integer> batchUploadMenus(List<MenuDTO> menuDTOs, String userId);
    
    /**
     * 按分类浏览餐单
     * @param categoryId 分类ID
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return IPage<MenuVO> 分页的餐单视图对象列表
     */
    IPage<MenuVO> getMenusByCategory(Integer categoryId, int pageNum, int pageSize);
    
    /**
     * 按热门程度浏览餐单
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return IPage<MenuVO> 分页的热门餐单视图对象列表
     */
    IPage<MenuVO> getHotMenus(int pageNum, int pageSize);
    
    /**
     * 按最新上传浏览餐单
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return IPage<MenuVO> 分页的最新餐单视图对象列表
     */
    IPage<MenuVO> getNewestMenus(int pageNum, int pageSize);
    
    /**
     * 搜索餐单
     * @param keyword 搜索关键词
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return IPage<MenuVO> 分页的搜索结果餐单视图对象列表
     */
    IPage<MenuVO> searchMenus(String keyword, int pageNum, int pageSize);
    
    /**
     * 获取餐单详情
     * @param menuId 餐单ID
     * @param userId 用户ID（可为null，未登录用户）
     * @return MenuDetailVO 包含食材和步骤的餐单详情视图对象
     */
    MenuDetailVO getMenuDetail(Integer menuId, String userId);
    
    /**
     * 收藏餐单
     * @param menuId 餐单ID
     * @param userId 用户ID
     * @return boolean 收藏是否成功
     */
    boolean collectMenu(Integer menuId, String userId);
    
    /**
     * 取消收藏餐单
     * @param menuId 餐单ID
     * @param userId 用户ID
     * @return boolean 取消收藏是否成功
     */
    boolean cancelCollectMenu(Integer menuId, String userId);
    
    /**
     * 获取用户收藏的餐单
     * @param userId 用户ID
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return IPage<MenuVO> 分页的用户收藏餐单视图对象列表
     */
    IPage<MenuVO> getUserCollectedMenus(String userId, int pageNum, int pageSize);
    
    /**
     * 分享餐单
     * @param menuId 餐单ID
     * @param userId 用户ID
     * @param channel 分享渠道（wechat_friend:微信好友,wechat_moments:朋友圈）
     * @return boolean 分享是否成功
     */
    boolean shareMenu(Integer menuId, String userId, String channel);
    
    /**
     * 获取用户创建的餐单
     * @param userId 用户ID
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return IPage<MenuVO> 分页的用户创建餐单视图对象列表
     */
    IPage<MenuVO> getUserCreatedMenus(String userId, int pageNum, int pageSize);
}