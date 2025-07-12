package com.menu.menu.controller;

import com.menu.menu.entity.Plan;
import com.menu.menu.service.PlanService;
import com.menu.menu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * 计划模块控制器
 */
@RestController
@RequestMapping("/api/plans")
public class PlanController {
    @Autowired
    private PlanService planService;

    /**
     * 获取计划列表
     */
    @GetMapping
    public Result listPlans() {
        List<Plan> plans = planService.listPlans();
        return Result.success(plans);
    }

    /**
     * 创建新计划
     */
    @PostMapping
    public Result createPlan(@Valid @RequestBody Plan plan) {
        boolean success = planService.createPlan(plan);
        return success ? Result.success("计划创建成功") : Result.error("计划创建失败");
    }

    /**
     * 更新计划
     */
    @PutMapping("/{id}")
    public Result updatePlan(@PathVariable Long id, @Valid @RequestBody Plan plan) {
        boolean success = planService.updatePlan(id, plan);
        return success ? Result.success("计划更新成功") : Result.error("计划更新失败");
    }

    /**
     * 删除计划
     */
    @DeleteMapping("/{id}")
    public Result deletePlan(@PathVariable Long id) {
        boolean success = planService.deletePlan(id);
        return success ? Result.success("计划删除成功") : Result.error("计划删除失败");
    }
}