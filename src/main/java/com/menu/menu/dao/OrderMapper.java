package com.menu.menu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.menu.menu.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}