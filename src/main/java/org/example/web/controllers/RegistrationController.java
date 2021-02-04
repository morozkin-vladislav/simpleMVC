package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.UserRepository;
import org.example.web.dto.LoginForm;
import org.example.web.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {
    private Logger logger = Logger.getLogger(LoginController.class);
    public UserRepository userRepo = new UserRepository();

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("User", new User());
        logger.info("GET registration.html");
        return "registration";
    }

    @PostMapping("/regUser")
    private String regUser(User user) {
        logger.info("TRY to REG user");
        userRepo.addUser(user);
        logger.info("REG - OK");
        return "redirect:/login";

    }

    @PostMapping("/auth")
    private String auth(LoginForm loginForm) {
        if (checkUser(loginForm)) {
            logger.info("GET book shelf");
            return "redirect:/books/shelf";
        } else {
            logger.info("GET /login");
            return "redirect:/login";
        }
    }


    public boolean checkUser(LoginForm loginForm) {
        logger.info("go method checkUser");
        return userRepo.checkUserRepo(loginForm.getUsername(), loginForm.getPassword());
    }


}


