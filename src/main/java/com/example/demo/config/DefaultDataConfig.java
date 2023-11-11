package com.example.demo.config;


import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.services.interfaces.IUserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DefaultDataConfig {

    private IUserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultDataConfig(IUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createDefaultUsers() {
        User john = User.builder().username("john").password(passwordEncoder.encode("123")).email("john@gmail.com").build();
        userService.createUser(john);

        User jane = User.builder().username("jane").password(passwordEncoder.encode("123")).email("jane@gmail.com").build();
        userService.createUser(jane);

        User admin = User.builder().email("abdul@gmail.com").username("admin").password(passwordEncoder.encode("admin")).roles(new HashSet<>(Arrays.asList(Role.ADMIN))).build();
        userService.createUser(admin);

        User moder = User.builder().email("allax@gmail.com").username("moder").password(passwordEncoder.encode("moder")).roles(new HashSet<>(Arrays.asList(Role.MODERATOR))).build();
        userService.createUser(moder);

        User umoderator = User.builder().email("gg@gmail.com").username("umoderator").password(passwordEncoder.encode("12345")).roles(new HashSet<>(Arrays.asList(Role.ADMIN, Role.MODERATOR))).build();
        userService.createUser(umoderator);
    }
}



