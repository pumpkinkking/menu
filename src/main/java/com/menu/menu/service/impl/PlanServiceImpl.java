package com.menu.menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.menu.menu.entity.Plan;
import com.menu.menu.exception.BusinessException;
import com.menu.menu.mapper.PlanMapper;
import com.menu.menu.service.PlanService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * 计划服务实现类
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements PlanService {
    @Override
    public List<Plan> listPlans() {
        return baseMapper.selectList(null);
    }

    @Override
    public Long createPlan(Plan plan) {
        plan.setCreateTime(new Date());
        plan.setUpdateTime(new Date());
        planMapper.insert(plan);
        return plan.getPlanId();
    }

    @Override
    public boolean updatePlan(Long id, Plan plan) {
        Plan existingPlan = getById(id);
        if (existingPlan == null) {
            throw new BusinessException(404, "计划不存在");
        }
        plan.setId(id);
        plan.setUpdateTime(new Date());
        return updateById(plan);
    }

    @Override
    public boolean deletePlan(Long id) {
        Plan existingPlan = getById(id);
        if (existingPlan == null) {
            throw new BusinessException(404, "计划不存在");
        }
        return removeById(id);
    }
}