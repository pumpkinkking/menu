package com.menu.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.menu.menu.entity.Plan;
import java.util.List;

/**
 * 计划服务接口
 */
public interface PlanService extends IService<Plan> {
    /**
     * 获取所有计划列表
     */
    List<Plan> listPlans();

    /**
     * 创建新计划
     */
    Long createPlan(Plan plan);

    /**
     * 更新计划
     */
    boolean updatePlan(Long id, Plan plan);

    /**
     * 删除计划
     */
    boolean deletePlan(Long id);
}