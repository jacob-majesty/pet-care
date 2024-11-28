package com.majesty.pet_care.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;   

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "patient_id")
public class Patient extends User {

    //@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

}
