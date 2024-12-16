package com.majesty.pet_care.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majesty.pet_care.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {


}
