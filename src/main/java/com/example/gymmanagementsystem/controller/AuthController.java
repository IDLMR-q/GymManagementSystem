package com.example.gymmanagementsystem.controller;

import com.example.gymmanagementsystem.entity.User;
import com.example.gymmanagementsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 显示注册页面，传入空User对象供表单绑定
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // 处理注册请求，使用表单绑定User对象
    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user, Model model) {
        if (userService.usernameExists(user.getUsername())) {
            model.addAttribute("error", "用户名已存在！");
            return "register";
        }

        // 加密密码后保存
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userService.saveUser(user);

        // 注册成功后重定向到登录页面，带参数提示
        return "redirect:/login?registered=true";
    }

    // 显示登录页面
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "registered", required = false) String registered, Model model) {
        if ("true".equals(registered)) {
            model.addAttribute("message", "注册成功，请登录");
        }
        return "login";
    }

    // 处理登录请求
    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model,
                               HttpSession session) {

        Optional<User> userOpt = userService.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                session.setAttribute("currentUser", user);
                return "redirect:/dashboard";
            }
        }

        model.addAttribute("error", "用户名或密码错误");
        return "login";
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
