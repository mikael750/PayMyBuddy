package com.paymybuddy.application.DTO;

import com.paymybuddy.application.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ContactDTO {

    @NotEmpty
    @Email
    String email;

    String name;

    public ContactDTO(User user){
        email = user.getEmail();
        name = user.getFullName();
    }
}
