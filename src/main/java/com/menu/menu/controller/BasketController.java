package com.menu.menu.controller;

import com.menu.menu.common.Result;
import com.menu.menu.dto.BasketDTO;
import com.menu.menu.service.BasketService;
import com.menu.menu.util.UserContextHolder;
import com.menu.menu.vo.BasketVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
@Tag(name = "菜篮子管理")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/getBasketList")
    @Operation(summary = "获取购物篮列表")
    public Result<List<BasketVO>> getBasketList() {
        String userId = UserContextHolder.getUserId();
        List<BasketVO> basketList = basketService.getBasketItemsByUserId(userId);
        return Result.success(basketList);
    }

    @PostMapping("/addToBasket")
    @Operation(summary = "添加商品到购物篮")
    public Result<Void> addToBasket(@RequestBody BasketDTO basketDTO) {
        String userId = UserContextHolder.getUserId();
        basketService.addToBasket(basketDTO, userId);
        return Result.success();
    }

  

    @DeleteMapping("removeFromBasket/{ingredientId}")
    @Operation(summary = "从菜篮子移除食材")
    public Result<Void> removeFromBasket(@PathVariable Integer ingredientId) {
        String userId = UserContextHolder.getUserId();
        basketService.removeFromBasket(ingredientId, userId);
        return Result.success();
    }
}