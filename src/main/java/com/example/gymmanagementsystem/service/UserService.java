package com.example.gymmanagementsystem.service;

import com.example.gymmanagementsystem.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    User saveUser(User user);
    void deleteUser(Long id);

    boolean usernameExists(String username);  // ✅ 新增方法用于注册校验
}
