package com.samir.ecommerce.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
        private Long id;
        private String name;
        private String email;
        private String role;
    }

