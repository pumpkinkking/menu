package com.menu.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.menu.menu.entity.Order;

/**
 * 订单数据访问接口
 * 继承BaseMapper以获得MyBatis-Plus提供的CRUD操作
 */
public interface OrderMapper extends BaseMapper<Order> {
}