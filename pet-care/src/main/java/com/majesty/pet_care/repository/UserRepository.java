package com.majesty.pet_care.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majesty.pet_care.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
