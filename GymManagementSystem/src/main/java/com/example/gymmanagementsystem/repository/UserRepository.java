package com.example.gymmanagementsystem.repository;

import com.example.gymmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);      // 用于登录等查询
    boolean existsByUsername(String username);           // 用于注册时校验用户名重复
}
