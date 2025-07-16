package com.menu.menu.service;

import com.menu.menu.dto.OrderDTO;
import com.menu.menu.vo.OrderVO;
import java.util.List;

public interface OrderService {
    List<OrderVO> getOrdersByUserId(String userId);
    Integer createOrder(OrderDTO orderDTO, String userId);
    OrderVO getOrderDetail(Integer id, String userId);
}