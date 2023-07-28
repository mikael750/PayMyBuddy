package com.paymybuddy.application.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class ContactController {

    @GetMapping(value =  {"/contact"})
    public String contactPage(){
        return "contact";
    }

}
