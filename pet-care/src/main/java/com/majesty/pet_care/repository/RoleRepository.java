package com.majesty.pet_care.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majesty.pet_care.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String roleName);
}
