package com.example.gymmanagementsystem.controller;

import com.example.gymmanagementsystem.entity.User;
import com.example.gymmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user, Model model) {
        if (userService.usernameExists(user.getUsername())) {
            model.addAttribute("error", "用户名已存在！");
            return "register";
        }

        user.setRole("USER"); // 默认注册为普通用户
        userService.saveUser(user);
        return "redirect:/login?registered=true";
    }
}
