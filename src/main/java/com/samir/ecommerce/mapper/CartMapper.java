package com.samir.ecommerce.mapper;

import com.samir.ecommerce.dto.CartDto.CartItemResponse;
import com.samir.ecommerce.dto.CartDto.CartResponse;
import com.samir.ecommerce.entity.Cart;
import com.samir.ecommerce.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    public CartItemResponse toItemResponse(CartItem item) {
        CartItemResponse response = new CartItemResponse();
        response.setCartItemId(item.getId());
        response.setProductId(item.getProduct().getId());
        response.setProductName(item.getProduct().getName());
        response.setPrice(item.getProduct().getPrice());
        response.setQuantity(item.getQuantity());
        return response;
    }

    public CartResponse toCartResponse(Cart cart) {
        CartResponse response = new CartResponse();

        List<CartItemResponse> items = cart.getCartItems()
                .stream()
                .map(this::toItemResponse)
                .toList();

        response.setItems(items);

        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        response.setTotalPrice(total);

        return response;
    }
}
