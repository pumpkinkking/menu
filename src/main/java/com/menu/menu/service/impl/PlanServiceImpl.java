package com.menu.menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.menu.menu.entity.Plan;
import com.menu.menu.entity.PlanVO;
import com.menu.menu.exception.BusinessException;
import com.menu.menu.mapper.PlanMapper;
import com.menu.menu.service.PlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 计划服务实现类
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements PlanService {
    
    @Autowired
    private PlanMapper planMapper;
    
    @Override
    public List<PlanVO> selectByUserIdAndDate(String userId, LocalDate date) {
        return planMapper.selectByUserIdAndDate(userId, date);
    }

    @Override
    public Integer createPlan(Plan plan) {
        return planMapper.insert(plan);
    }

    @Override
    public Boolean updatePlan(Integer id, Plan plan) {
        Plan existingPlan = getById(id);
        if (existingPlan == null) {
            throw new BusinessException(404, "计划不存在");
        }
        plan.setPlanId(id);
        return updateById(plan);
    }

    @Override
    public Boolean deletePlan(Integer id) {
        Plan existingPlan = getById(id);
        if (existingPlan == null) {
            throw new BusinessException(404, "计划不存在");
        }
        return removeById(id);
    }
}