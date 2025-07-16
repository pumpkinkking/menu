package com.menu.menu.controller;

import com.menu.menu.common.Result;
import com.menu.menu.dto.OrderDTO;
import com.menu.menu.service.OrderService;
import com.menu.menu.util.UserContextHolder;
import com.menu.menu.vo.OrderVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "订单管理")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrders")
    @Operation(summary = "获取订单列表")
    public Result<List<OrderVO>> getOrders() {
        String userId = UserContextHolder.getUserId();
        List<OrderVO> orders = orderService.getOrdersByUserId(userId);
        return Result.success(orders);
    }

    @PostMapping("/create")
    @Operation(summary = "创建订单")
    public Result<Integer> createOrder(@RequestBody OrderDTO orderDTO) {
        String userId = UserContextHolder.getUserId();
        Integer orderId = orderService.createOrder(orderDTO, userId);
        return Result.success(orderId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取订单详情")
    public Result<OrderVO> getOrderDetail(@PathVariable Integer id) {
        String userId = UserContextHolder.getUserId();
        OrderVO orderVO = orderService.getOrderDetail(id, userId);
        return Result.success(orderVO);
    }
}