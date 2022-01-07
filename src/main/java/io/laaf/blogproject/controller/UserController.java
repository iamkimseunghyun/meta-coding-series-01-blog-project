package io.laaf.blogproject.controller;

import io.laaf.blogproject.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class UserController {

    @GetMapping("/user/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("user", new User());
        return "user/joinForm";
    }

    @GetMapping("/user/loginForm")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "user/loginForm";
    }
}
