package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.UserDTO;
import com.paymybuddy.application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping(value =  {"/login"})
    public String loginPage(){
        return "login";
    }



    @GetMapping(value = "/registration")
    public String registrationPage(Model registrationFormDto){
        registrationFormDto.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration/save")
    public String registration(@Valid @ModelAttribute("user")UserDTO userDTO, BindingResult result, Model registrationFormDto) {
        if (result.hasErrors()) {
            registrationFormDto.addAttribute("user", userDTO);
            return "registration";
        }

        try {
            userService.saveUser(userDTO);
            return "redirect:/registration?success";
        } catch (Exception e) {
            result.rejectValue("email", null, "An account already exist with the email : " + userDTO.getEmail());
            return "registration";
        }
    }
}
