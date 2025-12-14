package com.samir.ecommerce.controller;

import com.samir.ecommerce.dto.OrderDto.OrderResponse;
import com.samir.ecommerce.service.OrderService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    public final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/userId/{userId}")
    public ResponseEntity<OrderResponse> createOrder(@PathVariable("userId") Long userId) {
        OrderResponse orderResponse = orderService.createOrder(userId);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/orderId/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("orderId") Long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@PathVariable("userId") Long userId) {
        List<OrderResponse> orderResponses = orderService.getUserOrders(userId);
        return ResponseEntity.ok(orderResponses);
    }

}
