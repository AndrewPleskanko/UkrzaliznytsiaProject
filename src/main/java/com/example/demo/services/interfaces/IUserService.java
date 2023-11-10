package com.example.demo.services.interfaces;

import com.example.demo.entity.User;

import java.util.List;

public interface IUserService {
    boolean userExist(String username);

    void createUser(User user);

    User get(Long id);

    List<User> getList();
}
