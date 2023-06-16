package com.paymybuddy.application.repository;

import com.paymybuddy.application.models.ERole;
import com.paymybuddy.application.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
