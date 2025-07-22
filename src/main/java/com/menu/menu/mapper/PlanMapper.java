package com.menu.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.menu.menu.entity.Plan;
import com.menu.menu.entity.PlanVO;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 计划数据访问接口
 */
@Mapper
public interface PlanMapper extends BaseMapper<Plan> {
        /**
     * 根据用户ID和日期查询计划列表
     * @param userId 用户ID
     * @param date 查询日期
     * @return 计划列表
     */
    List<PlanVO> selectByUserIdAndDate(String userId, LocalDate date);

}