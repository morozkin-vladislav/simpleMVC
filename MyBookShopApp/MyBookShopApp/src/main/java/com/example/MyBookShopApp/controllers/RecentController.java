package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecentController {
    public RecentController() {
    }



    @GetMapping("/recent")
    public String authorsPage(){
        return "/books/recent";
    }
}
