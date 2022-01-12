package io.laaf.blogproject.controller;

import io.laaf.blogproject.config.auth.PrincipalDetail;
import io.laaf.blogproject.config.auth.PrincipalDetailService;
import io.laaf.blogproject.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/** 허용

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("user", new User());
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {

        return "user/updateForm";
    }
}
