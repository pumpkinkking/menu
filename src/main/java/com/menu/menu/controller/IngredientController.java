package com.menu.menu.controller;

import com.menu.menu.common.Result;
import com.menu.menu.dto.IngredientDTO;
import com.menu.menu.service.IngredientService;
import com.menu.menu.vo.IngredientVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@Tag(name = "食材管理")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    @Operation(summary = "获取食材列表")
    public Result<List<IngredientVO>> getIngredients(HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        List<IngredientVO> ingredients = ingredientService.getIngredientsByUserId(userId);
        return Result.success(ingredients);
    }

    @PostMapping
    @Operation(summary = "添加食材")
    public Result<Long> addIngredient(@RequestBody IngredientDTO ingredientDTO, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        Long ingredientId = ingredientService.addIngredient(ingredientDTO, userId);
        return Result.success(ingredientId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除食材")
    public Result<Boolean> deleteIngredient(@PathVariable Long id, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        boolean success = ingredientService.deleteIngredient(id, userId);
        return Result.success(success);
    }

    private Long getCurrentUserId(HttpServletRequest request) {
        // 实际项目中从token或session获取
        return 1L;
    }
}