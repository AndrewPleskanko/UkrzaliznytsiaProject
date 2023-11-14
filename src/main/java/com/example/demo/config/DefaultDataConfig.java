package com.example.demo.config;


import com.example.demo.dto.UserSignUpRequest;
import com.example.demo.entity.Role;
import com.example.demo.services.interfaces.IUserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DefaultDataConfig {

    private IUserService userService;

    @Autowired
    public DefaultDataConfig(IUserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void createDefaultUsers() {
        UserSignUpRequest johnRequest = new UserSignUpRequest();
        johnRequest.setUsername("john");
        johnRequest.setPassword("123");
        johnRequest.setEmail("john@gmail.com");
        userService.saveUser(johnRequest);

        UserSignUpRequest janeRequest = new UserSignUpRequest();
        janeRequest.setUsername("jane");
        janeRequest.setPassword("123");
        janeRequest.setEmail("jane@gmail.com");
        userService.saveUser(janeRequest);

        UserSignUpRequest adminRequest = new UserSignUpRequest();
        adminRequest.setUsername("admin");
        adminRequest.setPassword("admin");
        adminRequest.setEmail("abdul@gmail.com");
        adminRequest.setRoleList(new HashSet<>(Arrays.asList(Role.ADMIN)));
        userService.saveUser(adminRequest);

        UserSignUpRequest moderRequest = new UserSignUpRequest();
        moderRequest.setUsername("moder");
        moderRequest.setPassword("moder");
        moderRequest.setEmail("allax@gmail.com");
        moderRequest.setRoleList(new HashSet<>(Arrays.asList(Role.MODERATOR)));
        userService.saveUser(moderRequest);

        UserSignUpRequest umoderatorRequest = new UserSignUpRequest();
        umoderatorRequest.setUsername("umoderator");
        umoderatorRequest.setPassword("12345");
        umoderatorRequest.setEmail("gg@gmail.com");
        umoderatorRequest.setRoleList(new HashSet<>(Arrays.asList(Role.ADMIN, Role.MODERATOR)));
        userService.saveUser(umoderatorRequest);
    }
}



