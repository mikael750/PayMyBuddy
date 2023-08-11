package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @Autowired
    private UserService userService;

    @GetMapping(value =  {"/contact"})
    public ResponseEntity<String> contactPage(Model model, Authentication principal){
        model.addAttribute("contact_list", userService.findContactList(principal.getName()));
        model.addAttribute("add_contact", new ContactDTO());
        return ResponseEntity.ok("contact");
    }

}
