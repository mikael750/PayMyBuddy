package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

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

    @PostMapping("/contact")
    public ResponseEntity<String> addContact(@Valid @ModelAttribute("add_contact") ContactDTO contactDTO, BindingResult result, Model contactFormDTO, Authentication principal){
        if (result.hasErrors()) {
            return contactPage(contactFormDTO, principal);
        }
        try {
            userService.addContact(contactDTO, principal.getName());
        } catch (Exception e) {
            contactFormDTO.addAttribute("message_error", e.getMessage());
            return contactPage(contactFormDTO, principal);
        }

        return ResponseEntity.ok("redirect:/contact?success");
    }
}
