package com.example.demo.services.interfaces;

import com.example.demo.dto.UserSignUpRequest;
import com.example.demo.entity.User;

import java.util.List;

public interface IUserService {
    boolean userExist(String username);

    void saveUser(UserSignUpRequest request);

    User get(Long id);

    List<User> getList();
}
