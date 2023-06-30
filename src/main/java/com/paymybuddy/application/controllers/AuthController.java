package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping(value =  {"/login"})
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registrationPage(Model model){
        model.addAttribute("user", new UserDTO());
        return "registration";
    }
}
