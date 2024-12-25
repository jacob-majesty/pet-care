package com.majesty.pet_care.dto;

import lombok.Data;

@Data
public class PetDto {

    private long id;
    private String name;
    private String type;
    private String color;
    private String breed;
    private int age;

}
