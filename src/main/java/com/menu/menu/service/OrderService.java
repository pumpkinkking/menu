package com.menu.menu.service;

import com.menu.menu.dto.OrderDTO;
import com.menu.menu.vo.OrderVO;
import java.util.List;

public interface OrderService {
    List<OrderVO> getOrdersByUserId(Long userId);
    Long createOrder(OrderDTO orderDTO, Long userId);
    OrderVO getOrderDetail(Long id, Long userId);
}