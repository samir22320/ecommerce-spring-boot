package com.samir.ecommerce.service.OrderService;

import com.samir.ecommerce.dto.CartDto.CartResponse;
import com.samir.ecommerce.dto.OrderDto.OrderResponse;
import com.samir.ecommerce.entity.*;
import com.samir.ecommerce.execption.ResourceNotFoundException;
import com.samir.ecommerce.mapper.OrderMapper;
import com.samir.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
   private final CartItemRepository cartItemRepository;
    public final OrderItemRepository orderItemRepository;
    public final UserRepository userRepository;
    public final CartRepository cartRepository;
    public final OrderMapper orderMapper;
    public final OrderRepository orderRepository;
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, CartRepository cartRepository, UserRepository userRepository, OrderItemRepository orderItemRepository, CartItemRepository cartItemRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public OrderResponse createOrder(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("User Id Not Found " + userId));
        if(cart.getCartItems().isEmpty())
            throw new IllegalStateException("Cannot create order from empty cart");
        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order = orderRepository.save(order);
        double totalOrderPrice = 0;
        for(CartItem cartItem : cart.getCartItems())
        {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            double itemTotal = cartItem.getQuantity() * cartItem.getProduct().getPrice();
            orderItem.setPriceAtPurchase(itemTotal);
            totalOrderPrice += itemTotal;
            orderItemRepository.save(orderItem);
            order.getItems().add(orderItem);
        }
        order.setTotalPrice(totalOrderPrice);
        orderRepository.save(order);
        cartItemRepository.deleteAll(cart.getCartItems());
        cart.getCartItems().clear();
        cartRepository.save(cart);
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Order Id Not Found " + orderId));
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public Page<OrderResponse> getUserOrders(Long userId, Pageable pageable) {
        Page<Order> orders = orderRepository.findById(userId,pageable);
        return orders.map(orderMapper::toOrderResponse);
    }

//    @Override
//    public List<OrderResponse> getUserOrders(Long userId) {
//        List<Order> orders = orderRepository.findByUserId(userId);
//        return orders.stream().map(orderMapper::toOrderResponse)
//                .toList();
//    }

}
