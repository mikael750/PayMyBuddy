package com.paymybuddy.application.models;

import com.paymybuddy.application.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String birthdate;

    private BigDecimal solde = BigDecimal.valueOf(0);

    public User(UserDTO userDTO, PasswordEncoder passwordEncoder){
        this.setFirstName(userDTO.getFirstName());
        this.setLastName(userDTO.getLastName());
        this.setEmail(userDTO.getEmail());
        this.setBirthdate(userDTO.getBirthdate());
        this.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }

    @ManyToMany(
            cascade = CascadeType.PERSIST
    )
    private List<User> contacts = new ArrayList<>();


    /**
     * retourne le nom et prenom du sender et receiver.
     * @return firstName lastName
     */
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Ajout un montant d'argent a la solde de l'Utilisateur
     * @param amount
     * @return
     */
    public User creditBalance(BigDecimal amount){
        if (solde.add(amount).compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("The Balance doesn't have enough !");
        }
        setSolde(solde.add(amount));
        return this;
    }

    /**
     * Retire un montant d'argent a la solde de l'Utilisateur
     * @param amount
     * @return
     */
    public User debitBalance(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) <= 0 ){
            throw new RuntimeException("The Balance cannot be less or equal than zero");
        }

        if(solde.compareTo(amount) < 0 ) {
            throw new RuntimeException("The Balance of the sender doesn't have enough !");
        }
        solde = solde.subtract(amount);

        return this;
    }

    /**
     * Ajout un contact a l'utilisateur
     * @param user
     * @return
     */
    public User addContact(User user){
        if (contacts.contains(user)) {
            throw new RuntimeException(user.getEmail() + " is already in your contacts");
        }
        contacts.add(user);
        return this;
    }

}
