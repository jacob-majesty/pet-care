package com.majesty.pet_care.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Review {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String feedback;
    private int stars;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id")
    private User veterinarian;
    
    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User patient;



}
