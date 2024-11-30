package com.majesty.pet_care.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majesty.pet_care.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
