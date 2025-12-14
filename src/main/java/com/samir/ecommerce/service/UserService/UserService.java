package com.samir.ecommerce.service.UserService;

import com.samir.ecommerce.dto.UserDto.UserRequest;
import com.samir.ecommerce.dto.UserDto.UserResponse;
import com.samir.ecommerce.entity.User;

import java.util.List;

public interface UserService {
    UserResponse register(UserRequest userRequest);

    UserResponse getUserById(Long userId);

    List<UserResponse> getAllUser();

    String verify(User user);
}
