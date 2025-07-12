package com.menu.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.menu.menu.entity.Plan;
import org.apache.ibatis.annotations.Mapper;

/**
 * 计划数据访问接口
 */
@Mapper
public interface PlanMapper extends BaseMapper<Plan> {
}