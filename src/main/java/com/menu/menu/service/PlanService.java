package com.menu.menu.service;

import com.menu.menu.entity.Plan;
import com.menu.menu.entity.PlanVO;

import java.time.LocalDate;
import java.util.List;

/**
 * 计划服务接口
 */
public interface PlanService {

    /**
     * 根据用户ID和日期查询计划列表
     * @param userId 用户ID
     * @param date 查询日期
     * @return 计划列表
     */
    List<PlanVO> selectByUserIdAndDate(String userId, LocalDate date);

    /**
     * 创建新计划
     * @param plan 计划实体
     * @return 新创建计划的ID
     */
    Integer createPlan(Plan plan);

    /**
     * 更新计划
     * @param id 计划ID
     * @param plan 计划实体
     * @return 是否更新成功
     */
    Boolean updatePlan(Integer id, Plan plan);

    /**
     * 删除计划
     * @param id 计划ID
     * @return 是否删除成功
     */
    Boolean deletePlan(Integer id);
}