package com.menu.menu.controller;

import com.menu.menu.util.UserContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.menu.menu.common.Result;
import com.menu.menu.entity.Plan;
import com.menu.menu.entity.PlanVO;
import com.menu.menu.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    /**
     * 根据日期查询用户计划列表
     * @param dateStr 日期字符串，格式为yyyy-MM-dd
     * @return 计划列表
     */
    @GetMapping("/listPlans/{dateStr}")
    public Result<List<PlanVO>> listPlans(@PathVariable String dateStr) {
        // 从ThreadLocal获取当前用户ID
        String userId = UserContextHolder.getUserId();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<PlanVO> plans = planService.selectByUserIdAndDate(userId, date);
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