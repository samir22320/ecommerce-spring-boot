package com.samir.ecommerce.service.CartService;

import com.samir.ecommerce.dto.CartDto.AddToCartRequest;
import com.samir.ecommerce.dto.CartDto.CartResponse;
import com.samir.ecommerce.entity.Cart;
import com.samir.ecommerce.entity.CartItem;
import com.samir.ecommerce.entity.Product;
import com.samir.ecommerce.entity.User;
import com.samir.ecommerce.execption.ResourceNotFoundException;
import com.samir.ecommerce.mapper.CartMapper;
import com.samir.ecommerce.repository.CartItemRepository;
import com.samir.ecommerce.repository.CartRepository;
import com.samir.ecommerce.repository.ProductRepository;
import com.samir.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

@Service
public class CartServiceImpl implements CartService{
   public final ProductRepository productRepository;
    public final CartMapper cartMapper;
    public final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(CartRepository cartRepository, CartMapper cartMapper, ProductRepository productRepository, UserRepository userRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }


    @Override
    public CartResponse getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("User id Not Found " + userId));
        return cartMapper.toCartResponse(cart);
    }

    @Override
    public CartResponse addToCart(Long userId, AddToCartRequest add) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("User id Not Found " + userId));
        Product product = productRepository.findById(add.getProductId())
                .orElseThrow(()-> new ResourceNotFoundException("Product Id Not Found "+ add.getProductId()));
        CartItem existingItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);
        if(existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + add.getQuantity());
        }else
        {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setQuantity(add.getQuantity());
            newItem.setProduct(product);
            CartItem savedItem = cartItemRepository.save(newItem);
            cart.getCartItems().add(savedItem);
        }
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toCartResponse(savedCart);
    }

    @Override
    public CartResponse removeFromCart(Long userId, Long cartItemId) {
        Cart cart =cartRepository.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("User id Not Found " + userId));
        CartItem item = cart.getCartItems()
                .stream()
                .filter(i -> i.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException("CartItem not found with id " + cartItemId));
        cart.getCartItems().remove(item);
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toCartResponse(savedCart);
    }

    @Override
    public CartResponse clearCart(Long userId) {
        Cart cart =cartRepository.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("User id Not Found " + userId));
        cart.getCartItems().clear();
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toCartResponse(savedCart);
    }


}
