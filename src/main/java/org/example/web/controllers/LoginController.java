package org.example.web.controllers;

import org.apache.log4j.Logger;

import org.example.app.services.LoginService;
import org.example.web.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/login")
public class LoginController {
    //apache логирование
    private Logger logger = Logger.getLogger(LoginController.class);
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login(Model model) {

        logger.info("GET /login return login_page.html");
        model.addAttribute("loginForm", new LoginForm());
        return "login_page";
    }


    @GetMapping("/registration")
    public String registration() {
        return "redirect:/registration";
    }


}
