package com.menu.menu.controller;

import com.menu.menu.common.Result;
import com.menu.menu.dto.IngredientDTO;
import com.menu.menu.service.IngredientService;
import com.menu.menu.util.UserContextHolder;
import com.menu.menu.vo.IngredientVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "食材管理")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/getIngredientList")
    @Operation(summary = "获取食材列表")
    public Result<List<IngredientVO>> getIngredientList() {
        String userId = UserContextHolder.getUserId();
        List<IngredientVO> ingredientList = ingredientService.getIngredientsByUserId(userId);
        return Result.success(ingredientList);
    }

    @PostMapping("addIngredient/getIngredientList")
    @Operation(summary = "添加食材")
    public Result<Integer> addIngredient(@RequestBody IngredientDTO ingredientDTO) {
        String userId = UserContextHolder.getUserId();
        Integer ingredientId = ingredientService.addIngredient(ingredientDTO, userId);
        return Result.success(ingredientId);
    }

    @DeleteMapping("deleteIngredient/{id}")
    @Operation(summary = "删除食材")
    public Result<Void> deleteIngredient(@PathVariable Integer id) {
        String userId = UserContextHolder.getUserId();
        ingredientService.deleteIngredient(id, userId);
        return Result.success();
    }
}