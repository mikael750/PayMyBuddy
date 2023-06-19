package com.paymybuddy.application.models;

import com.paymybuddy.application.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
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
        this.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }
}
