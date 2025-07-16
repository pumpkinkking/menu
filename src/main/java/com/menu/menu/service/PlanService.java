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
    Integer createPlan(Plan plan);

    /**
     * 更新计划
     */
    Boolean updatePlan(Integer id, Plan plan);

    /**
     * 删除计划
     */
    Boolean deletePlan(Integer id);
}