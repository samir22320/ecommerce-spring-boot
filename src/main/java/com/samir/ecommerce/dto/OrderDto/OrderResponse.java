package com.samir.ecommerce.dto.OrderDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;
}
