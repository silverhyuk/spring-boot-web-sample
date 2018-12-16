package com.cafe24.websample.web.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("/login.ws")
    public String loginForm(HttpServletRequest req, Model model) {
        String referer = req.getHeader("Referer");
        String prevPage = (String) req.getSession().getAttribute("prevPage");
        if (prevPage != null) {
            req.getSession().setAttribute("prevPage", referer);
        }

        String userId = (String) req.getAttribute("userId");
        model.addAttribute("userId", userId);
        String error = (String) req.getParameter("error");
        if(error != null)
        logger.debug("error : {}", error);

        return "auth/login";
    }
}
