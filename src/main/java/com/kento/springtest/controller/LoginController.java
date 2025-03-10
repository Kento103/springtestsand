package com.kento.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login"; // ログインページの表示
    }

    @GetMapping("/")
    public String top() {
        return "top"; // トップページの表示
    }

    @GetMapping("/user")
    public String user() {
        return "user"; // ユーザーページの表示
    }
}
