package com.samir.ecommerce.service.OrderService;

import com.samir.ecommerce.dto.OrderDto.OrderResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(Long userId);

    OrderResponse getOrderById(Long orderId);

    List<OrderResponse> getUserOrders(Long userId);
}
