package com.kento.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kento.springtest.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistarController {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, Model model) {
        try {
            // 登録成功した時の処理
            userService.registerUser(username, email, password); // @RequestParamから値を受け取る
            model.addAttribute("success", "ユーザー登録が完了しました");
            return "login";
        } catch (Exception e) {
            // 登録失敗したときの処理
            model.addAttribute("error", "登録に失敗しました");
            return "register";
        }
    }
}
