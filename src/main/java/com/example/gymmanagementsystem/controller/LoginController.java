package com.example.gymmanagementsystem.controller;

import com.example.gymmanagementsystem.entity.User;
import com.example.gymmanagementsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")  // 统一前缀，避免路径冲突
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // 登录页面
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {

        Optional<User> userOpt = userService.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // 这里建议用密码加密匹配，示例里暂时保持明文比较（不安全，推荐加密）
            if (password.equals(user.getPassword())) {
                session.setAttribute("currentUser", user);
                return "redirect:/dashboard";
            }
        }
        model.addAttribute("error", "用户名或密码错误");
        return "login";
    }

    // 退出登录，路径修改为 /user/logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }
}
