package com.paymybuddy.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/home")
    public String homePage(){
        return "home";
    }

    @GetMapping(value = "/homeoff")
    public String homeOffPage(){
        return "homeoff";
    }
}
