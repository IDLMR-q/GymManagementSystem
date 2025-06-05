package com.example.gymmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")  // 类级别路径前缀
public class AdminController {

    @GetMapping("/dashboard")  // 访问路径为 /admin/dashboard
    public String dashboard() {
        return "admin_dashboard";  // 返回 admin_dashboard.html 模板
    }
}
