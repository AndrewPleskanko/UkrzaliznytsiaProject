package com.example.demo.services;

import com.example.demo.dto.UserSignUpRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper {

    public static User mapUserRequest2User(UserSignUpRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);

        return user;
    }
}
