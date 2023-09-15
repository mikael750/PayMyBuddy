package com.paymybuddy.application.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotEmpty
    @Size(max = 20)
    private String firstName;

    @NotEmpty
    @Size(max = 20)
    private String lastName;

    @NotEmpty
    @Email
    @Size(max = 50)
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String birthdate;
}
