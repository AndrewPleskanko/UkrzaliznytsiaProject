package com.example.demo.services;


import com.example.demo.dto.UserSignUpRequest;
import com.example.demo.entity.Role;
import com.example.demo.repositories.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private static final Logger logger = LogManager.getLogger();

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public boolean userExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    @Transactional
    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<User> getList() {
        return userRepository.findAll();
    }

    @Transactional
    public void saveUser(UserSignUpRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Role> roles = request.getRoleList();
        if (roles == null || roles.isEmpty()) {
            roles = new HashSet<>(Collections.singletonList(Role.USER));
        }
        user.setRoles(roles);
        userRepository.save(user);
    }
}
