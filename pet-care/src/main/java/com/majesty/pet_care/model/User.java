package com.majesty.pet_care.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.persistence.Inheritance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
public class User {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    @Column(name = "mobile")
    private String phoneNumber;
    private String email;
    private String password;
    private String UserType;
    private boolean isEnabled;

    @CreationTimestamp
    private LocalDate createdAt;

    @Transient
    private String specialization;

    @Transient
    private List<Appointment> appointments = new ArrayList<>();

    @Transient
    List<Review> reviews = new ArrayList<>();

    @OneToOne(cascade = jakarta.persistence.CascadeType.ALL, fetch = jakarta.persistence.FetchType.EAGER, orphanRemoval = true)
    private Photo photo;

    public void removeUserPhoto(){
        if(this.getPhoto() != null){
            this.setPhoto(null);
        }
    }

}
