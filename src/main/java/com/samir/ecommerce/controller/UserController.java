package com.samir.ecommerce.controller;

import com.samir.ecommerce.dto.UserDto.UserRequest;
import com.samir.ecommerce.dto.UserDto.UserResponse;
import com.samir.ecommerce.entity.User;
import com.samir.ecommerce.service.UserService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    public final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest)
    {
        UserResponse userResponse = userService.register(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
    @GetMapping("/userId/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") Long userId)
    {
        UserResponse userResponse = userService.getUserById(userId);
        return ResponseEntity.ok(userResponse);
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser()
    {
        List<UserResponse> userResponses = userService.getAllUser();
        return ResponseEntity.ok(userResponses);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user)
    {
        return userService.verify(user);
    }

}
