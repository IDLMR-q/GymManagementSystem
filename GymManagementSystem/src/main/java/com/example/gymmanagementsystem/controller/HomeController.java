package com.example.gymmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")  // 访问根路径时重定向登录页面
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/dashboard")  // 普通用户首页，路径为 /dashboard
    public String dashboard() {
        return "dashboard";  // 返回 dashboard.html 模板
    }
}
