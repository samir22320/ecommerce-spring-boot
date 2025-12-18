package com.samir.ecommerce.service.OrderService;

import com.samir.ecommerce.dto.OrderDto.OrderResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(Long userId);

    OrderResponse getOrderById(Long orderId);

    Page<OrderResponse> getUserOrders(Long userId, Pageable pageable);

//    List<OrderResponse> getUserOrders(Long userId);

}
