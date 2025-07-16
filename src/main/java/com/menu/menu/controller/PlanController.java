package com.menu.menu.controller;

import com.menu.menu.entity.Plan;
import com.menu.menu.service.PlanService;
import com.menu.menu.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * 计划模块控制器
 */
@RestController
@RequestMapping("/plans")
public class PlanController {
    @Autowired
    private PlanService planService;

    /**
     * 获取计划列表
     */
    @GetMapping("/listPlans")
    public Result listPlans() {
        List<Plan> plans = planService.listPlans();
        return Result.success(plans);
    }

    /**
     * 创建新计划
     */
    @PostMapping("/createPlan")
    public Result<Integer> createPlan(@Valid @RequestBody Plan plan) {
        Integer planId = planService.createPlan(plan);
        return Result.success(planId);
    }

    /**
     * 更新计划
     */
    @PutMapping("/{id}")
    public Result<Boolean> updatePlan(@PathVariable Integer id, @Valid @RequestBody Plan plan) {
        Boolean planId = planService.updatePlan(id,plan);
        return Result.success(planId);
    }

    /**
     * 删除计划
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deletePlan(@PathVariable Integer id) {
        Boolean planId = planService.deletePlan(id);
        return Result.success(planId);
    }
}