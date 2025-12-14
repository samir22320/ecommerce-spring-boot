package com.samir.ecommerce.service.UserService;

import com.samir.ecommerce.dto.UserDto.UserRequest;
import com.samir.ecommerce.dto.UserDto.UserResponse;
import com.samir.ecommerce.entity.Cart;
import com.samir.ecommerce.entity.User;
import com.samir.ecommerce.execption.ResourceNotFoundException;
import com.samir.ecommerce.mapper.UserMapper;
import com.samir.ecommerce.repository.CartRepository;
import com.samir.ecommerce.repository.UserRepository;
import com.samir.ecommerce.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public final CartRepository cartRepository;
    public final UserMapper userMapper;
    public final UserRepository userRepository;
    public UserServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, UserMapper userMapper, UserRepository userRepository, CartRepository cartRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }


    @Override
    public UserResponse register(UserRequest userRequest) {
        User user =userMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(savedUser);
        cartRepository.save(cart);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User Id Not Found " + userId));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        return userMapper.toResponses(users);
    }

    @Override
    public String verify(User user) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if(authenticate.isAuthenticated())
            return jwtService.generateToken(user);
        return "failure";
    }


}
