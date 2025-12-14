package com.samir.ecommerce.service.CartService;

import com.samir.ecommerce.dto.CartDto.AddToCartRequest;
import com.samir.ecommerce.dto.CartDto.CartResponse;

public interface CartService {

    CartResponse getCart(Long userId);


    CartResponse addToCart(Long userId, AddToCartRequest add);

    CartResponse removeFromCart(Long userId, Long cartItemId);

    CartResponse clearCart(Long userId);
}
