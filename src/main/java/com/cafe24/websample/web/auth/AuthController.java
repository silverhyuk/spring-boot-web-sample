package com.cafe24.websample.web.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm(HttpServletRequest req) {
        String referer = req.getHeader("Referer");
        req.getSession().setAttribute("prevPage", referer);
        return "login";
    }
}
