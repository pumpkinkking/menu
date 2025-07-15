package com.menu.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.menu.menu.dto.OrderDTO;
import com.menu.menu.entity.Order;
import com.menu.menu.mapper.OrderMapper;
import com.menu.menu.service.OrderService;
import com.menu.menu.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderVO> getOrdersByUserId(String userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Order> orders = orderMapper.selectList(queryWrapper);
        return orders.stream().map(this::convertToVO).collect(Collectors.toList());
    }

    @Override
    public Integer createOrder(OrderDTO orderDTO, String userId) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setUserId(userId);
        order.setOrderNo(generateOrderNo());
        order.setStatus("待付款");
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        orderMapper.insert(order);
        return order.getOrderId();
    }

    @Override
    public OrderVO getOrderDetail(String id, String userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("user_id", userId);
        Order order = orderMapper.selectOne(queryWrapper);
        return convertToVO(order);
    }

    private OrderVO convertToVO(Order order) {
        OrderVO vo = new OrderVO();
        BeanUtils.copyProperties(order, vo);
        return vo;
    }

    private String generateOrderNo() {
        return "ORD" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 4);
    }
}