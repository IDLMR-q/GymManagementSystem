package com.example.gymmanagementsystem.service.impl;

import com.example.gymmanagementsystem.entity.User;
import com.example.gymmanagementsystem.repository.UserRepository;
import com.example.gymmanagementsystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // ✅ 新增：判断用户名是否已存在（注册时校验）
    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
