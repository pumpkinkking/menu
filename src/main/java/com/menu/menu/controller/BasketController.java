package com.menu.menu.controller;

import com.menu.menu.common.Result;
import com.menu.menu.dto.BasketDTO;
import com.menu.menu.service.BasketService;
import com.menu.menu.vo.BasketVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/basket")
@Tag(name = "菜篮子管理")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/getBasketItems")
    @Operation(summary = "获取菜篮子列表")
    public Result<List<BasketVO>> getBasketItems(HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        List<BasketVO> basketItems = basketService.getBasketItemsByUserId(userId);
        return Result.success(basketItems);
    }

    @PostMapping("/addToBasket")
    @Operation(summary = "添加食材到菜篮子")
    public Result<Long> addToBasket(@RequestBody BasketDTO basketDTO, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        Long basketId = basketService.addToBasket(basketDTO, userId);
        return Result.success(basketId);
    }

    @DeleteMapping("/{ingredientId}")
    @Operation(summary = "从菜篮子移除食材")
    public Result<Boolean> removeFromBasket(@PathVariable Long ingredientId, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        boolean success = basketService.removeFromBasket(ingredientId, userId);
        return Result.success(success);
    }

    private Long getCurrentUserId(HttpServletRequest request) {
        // 实际项目中从token或session获取
        return 1L;
    }
}