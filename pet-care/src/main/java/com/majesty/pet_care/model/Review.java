package com.majesty.pet_care.model;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String feedback;
    public int stars;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id")
    private User veterinarian;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User patient;

    public void removeRelationShip() {
        Optional.ofNullable(veterinarian)
                .ifPresent(vet -> vet.getReviews().remove(this));
        Optional.ofNullable(patient)
                .ifPresent(pat -> pat.getReviews()
                        .remove(this));
    }

}
