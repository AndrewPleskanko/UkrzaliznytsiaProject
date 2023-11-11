package com.example.demo.services;


import com.example.demo.dto.UserSignUpRequest;
import com.example.demo.entity.Role;
import com.example.demo.repositories.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private static final Logger logger = LogManager.getLogger();

    private UserRepository userRepository;


    @Override
    @Transactional
    public boolean userExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    @Transactional
    public void createUser(User user) {

        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>(Arrays.asList(Role.USER)));
        }

        userRepository.save(user);
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
        User user = UserMapper.mapUserRequest2User(request);
        userRepository.save(user);
    }
}
