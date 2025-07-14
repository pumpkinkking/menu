package com.menu.menu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.menu.menu.common.Result;
import com.menu.menu.dto.MenuDTO;
import com.menu.menu.service.FileUploadService;
import com.menu.menu.service.MenuService;
import com.menu.menu.vo.MenuDetailVO;
import com.menu.menu.vo.MenuVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.web.multipart.MultipartFile;

/**
 * 餐单管理控制器
 * 负责处理餐单相关的HTTP请求，包括餐单上传、查询、收藏、分享等功能
 */
@RestController
@RequestMapping("/menus")
@Tag(name = "餐单管理")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 初始化菜单图片上传
     */
    @PostMapping("/image/initialize")
    @Operation(summary = "初始化菜单图片上传")
    public Result<String> initializeMenuImageUpload(
            @RequestParam Long userId,
            @RequestParam String fileName) {
        String uploadId = fileUploadService.initializeUpload(userId, fileName);
        stringRedisTemplate.opsForValue().set("upload:menu:" + uploadId, userId.toString(), 24, TimeUnit.HOURS);
        return Result.success(uploadId);
    }

    /**
     * 上传菜单图片分片
     */
    @PostMapping("/image/chunk")
    @Operation(summary = "上传菜单图片分片")
    public Result<Void> uploadMenuImageChunk(
            @RequestParam String uploadId,
            @RequestParam int chunkNumber,
            @RequestParam MultipartFile chunkFile) {
        fileUploadService.uploadChunk(uploadId, chunkNumber, chunkFile);
        return Result.success();
    }

    /**
     * 完成菜单图片上传并生成缩略图
     */
    @PostMapping("/image/complete")
    @Operation(summary = "完成菜单图片上传")
    public Result<Map<String, String>> completeMenuImageUpload(
            @RequestParam String uploadId,
            @RequestParam(defaultValue = "200") int thumbnailWidth,
            @RequestParam(defaultValue = "200") int thumbnailHeight) {
        Map<String, String> urls = fileUploadService.completeMenuUpload(uploadId, thumbnailWidth, thumbnailHeight);
        stringRedisTemplate.delete("upload:menu:" + uploadId);
        return Result.success(urls);
    }

    /**
     * 上传餐单
     * @param menuDTO 餐单数据传输对象，包含餐单名称、描述、食材清单、烹饪步骤等信息
     * @param request HTTP请求对象，用于获取当前登录用户ID
     * @return Result<Long> 包含新上传餐单ID的成功响应
     */
    @PostMapping
    @Operation(summary = "上传餐单")
    public Result<Long> uploadMenu(@RequestBody MenuDTO menuDTO, HttpServletRequest request) {
        Long userId = getCurrentUserId(request); // 从请求中获取当前登录用户ID
        Long menuId = menuService.uploadMenu(menuDTO, userId);
        return Result.success(menuId);
    }

    /**
     * 批量上传餐单
     * @param menuDTOs 餐单数据传输对象列表
     * @param request HTTP请求对象，用于获取当前登录用户ID
     * @return Result<List<Long>> 包含多个新上传餐单ID的成功响应
     */
    @PostMapping("/batch")
    @Operation(summary = "批量上传餐单")
    public Result<List<Long>> batchUploadMenus(@RequestBody List<MenuDTO> menuDTOs, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        List<Long> menuIds = menuService.batchUploadMenus(menuDTOs, userId);
        return Result.success(menuIds);
    }

    /**
     * 按分类浏览餐单
     * @param categoryId 分类ID
     * @param pageNum 页码，默认1
     * @param pageSize 每页条数，默认10
     * @return Result<IPage<MenuVO>> 包含分页餐单列表的成功响应
     */
    @GetMapping("/category/{categoryId}")
    @Operation(summary = "按分类浏览餐单")
    public Result<IPage<MenuVO>> getMenusByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        IPage<MenuVO> menus = menuService.getMenusByCategory(categoryId, pageNum, pageSize);
        return Result.success(menus);
    }

    /**
     * 按热门程度浏览餐单
     * @param pageNum 页码，默认1
     * @param pageSize 每页条数，默认10
     * @return Result<IPage<MenuVO>> 包含分页热门餐单列表的成功响应
     */
    @GetMapping("/hot")
    @Operation(summary = "按热门程度浏览餐单")
    public Result<IPage<MenuVO>> getHotMenus(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        IPage<MenuVO> menus = menuService.getHotMenus(pageNum, pageSize);
        return Result.success(menus);
    }

    /**
     * 按最新上传浏览餐单
     * @param pageNum 页码，默认1
     * @param pageSize 每页条数，默认10
     * @return Result<IPage<MenuVO>> 包含分页最新餐单列表的成功响应
     */
    @GetMapping("/newest")
    @Operation(summary = "按最新上传浏览餐单")
    public Result<IPage<MenuVO>> getNewestMenus(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        IPage<MenuVO> menus = menuService.getNewestMenus(pageNum, pageSize);
        return Result.success(menus);
    }

    /**
     * 搜索餐单
     * @param keyword 搜索关键词
     * @param pageNum 页码，默认1
     * @param pageSize 每页条数，默认10
     * @return Result<IPage<MenuVO>> 包含分页搜索结果的成功响应
     */
    @GetMapping("/search")
    @Operation(summary = "搜索餐单")
    public Result<IPage<MenuVO>> searchMenus(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        IPage<MenuVO> menus = menuService.searchMenus(keyword, pageNum, pageSize);
        return Result.success(menus);
    }

    /**
     * 获取餐单详情
     * @param id 餐单ID
     * @param request HTTP请求对象，用于获取当前登录用户ID（可能为null）
     * @return Result<MenuDetailVO> 包含餐单详情的成功响应
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取餐单详情")
    public Result<MenuDetailVO> getMenuDetail(@PathVariable Long id, HttpServletRequest request) {
        Long userId = getCurrentUserId(request); // 可能为null（未登录用户）
        MenuDetailVO detailVO = menuService.getMenuDetail(id, userId);
        return Result.success(detailVO);
    }

    /**
     * 收藏餐单
     * @param id 餐单ID
     * @param request HTTP请求对象，用于获取当前登录用户ID
     * @return Result<Boolean> 收藏操作是否成功的响应
     */
    @PostMapping("/{id}/collect")
    @Operation(summary = "收藏餐单")
    public Result<Boolean> collectMenu(@PathVariable Long id, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        boolean success = menuService.collectMenu(id, userId);
        return Result.success(success);
    }

    /**
     * 取消收藏餐单
     * @param id 餐单ID
     * @param request HTTP请求对象，用于获取当前登录用户ID
     * @return Result<Boolean> 取消收藏操作是否成功的响应
     */
    @DeleteMapping("/{id}/collect")
    @Operation(summary = "取消收藏餐单")
    public Result<Boolean> cancelCollectMenu(@PathVariable Long id, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        boolean success = menuService.cancelCollectMenu(id, userId);
        return Result.success(success);
    }

    /**
     * 获取用户收藏的餐单
     * @param request HTTP请求对象，用于获取当前登录用户ID
     * @param pageNum 页码，默认1
     * @param pageSize 每页条数，默认10
     * @return Result<IPage<MenuVO>> 包含分页收藏餐单列表的成功响应
     */
    @GetMapping("/collections")
    @Operation(summary = "获取用户收藏的餐单")
    public Result<IPage<MenuVO>> getUserCollectedMenus(
            HttpServletRequest request,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        Long userId = getCurrentUserId(request);
        IPage<MenuVO> menus = menuService.getUserCollectedMenus(userId, pageNum, pageSize);
        return Result.success(menus);
    }

    /**
     * 分享餐单
     * @param id 餐单ID
     * @param channel 分享渠道（wechat_friend:微信好友,wechat_moments:朋友圈）
     * @param request HTTP请求对象，用于获取当前登录用户ID
     * @return Result<Boolean> 分享操作是否成功的响应
     */
    @PostMapping("/{id}/share")
    @Operation(summary = "分享餐单")
    public Result<Boolean> shareMenu(
            @PathVariable Long id,
            @RequestParam String channel,
            HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        boolean success = menuService.shareMenu(id, userId, channel);
        return Result.success(success);
    }

    /**
     * 获取用户创建的餐单
     * @param request HTTP请求对象，用于获取当前登录用户ID
     * @param pageNum 页码，默认1
     * @param pageSize 每页条数，默认10
     * @return Result<IPage<MenuVO>> 包含分页用户创建餐单列表的成功响应
     */
    @GetMapping("/my")
    @Operation(summary = "获取用户创建的餐单")
    public Result<IPage<MenuVO>> getUserCreatedMenus(
            HttpServletRequest request,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        Long userId = getCurrentUserId(request);
        IPage<MenuVO> menus = menuService.getUserCreatedMenus(userId, pageNum, pageSize);
        return Result.success(menus);
    }

    /**
     * 获取当前登录用户ID
     * 实际项目中应从认证信息（如token或session）中获取
     * @param request HTTP请求对象
     * @return Long 当前登录用户的ID，示例中返回固定值1L
     */
    private Long getCurrentUserId(HttpServletRequest request) {
        // 此处为示例，实际应从token或session中获取
        return 1L;
    }
}