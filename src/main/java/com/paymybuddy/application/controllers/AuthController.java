package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

@Controller
public class AuthController {
    @GetMapping(value =  {"/login"})
    public ResponseEntity<String> loginPage(){

        return ResponseEntity.ok("login");
    }

    @GetMapping(value = "/registration")
    public ResponseEntity<String> registrationPage(Model registrationFormDto){
        registrationFormDto.addAttribute("user", new UserDTO());
        return ResponseEntity.ok("registration");
    }
}
