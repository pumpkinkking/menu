package com.menu.menu.controller;

import com.menu.menu.common.Result;
import com.menu.menu.dto.OrderDTO;
import com.menu.menu.service.OrderService;
import com.menu.menu.vo.OrderVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
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
    public Result<List<OrderVO>> getOrders(HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        List<OrderVO> orders = orderService.getOrdersByUserId(userId);
        return Result.success(orders);
    }

    @PostMapping("/createOrder")
    @Operation(summary = "创建订单")
    public Result<Long> createOrder(@RequestBody OrderDTO orderDTO, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        Long orderId = orderService.createOrder(orderDTO, userId);
        return Result.success(orderId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取订单详情")
    public Result<OrderVO> getOrderDetail(@PathVariable Long id, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        OrderVO orderVO = orderService.getOrderDetail(id, userId);
        return Result.success(orderVO);
    }

    private Long getCurrentUserId(HttpServletRequest request) {
        // 实际项目中从token或session获取
        return 1L;
    }
}