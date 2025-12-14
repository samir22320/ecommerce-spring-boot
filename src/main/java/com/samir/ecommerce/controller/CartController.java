package com.samir.ecommerce.controller;

import com.samir.ecommerce.dto.CartDto.AddToCartRequest;
import com.samir.ecommerce.dto.CartDto.CartResponse;
import com.samir.ecommerce.service.CartService.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    public final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/userId/{userId}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable("userId") Long userId)
    {
        CartResponse cartResponse = cartService.getCart(userId);
        return ResponseEntity.ok(cartResponse);
    }
    @PostMapping("/userId/{userId}/add")
    public ResponseEntity<CartResponse> addToCart(@Valid @PathVariable("userId") Long userId, @RequestBody AddToCartRequest add)
    {
        CartResponse cartResponse = cartService.addToCart(userId,add);
        return ResponseEntity.ok(cartResponse);

    }
    @DeleteMapping("/userId/{userId}/remove/{cartItemId}")
    public ResponseEntity<CartResponse> removeFromCart
            (@PathVariable("userId")Long userId,@PathVariable("cartItemId")Long cartItemId)
    {
        CartResponse cartResponse = cartService.removeFromCart(userId,cartItemId);
        return ResponseEntity.ok(cartResponse);
    }

    @DeleteMapping("/userId/{userId}/clear")
    public ResponseEntity<CartResponse> clearCart(@PathVariable("userId")Long userId)
    {
        CartResponse cartResponse = cartService.clearCart(userId);
        return ResponseEntity.ok(cartResponse);
    }

}
