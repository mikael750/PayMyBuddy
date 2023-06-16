package com.paymybuddy.application.models;

import com.paymybuddy.application.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String bank;

    private BigDecimal solde = BigDecimal.valueOf(0);

    public UserAccount(UserDTO userDTO, PasswordEncoder passwordEncoder){
        this.setFirstName(userDTO.getFirstName());
        this.setLastName(userDTO.getLastName());
        this.setEmail(userDTO.getEmail());
        this.setBank(userDTO.getBank());
        //encrypt password
        this.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }
}
