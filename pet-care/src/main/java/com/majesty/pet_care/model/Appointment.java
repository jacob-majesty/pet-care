package com.majesty.pet_care.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.majesty.pet_care.enums.AppointmentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@JsonIgnoreProperties({"patient", "veterinarian"})
public class Appointment {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    private String reason;

    @CreationTimestamp
    private LocalDate createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime appointmentTime;
    private String appointmentNo;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @JoinColumn(name = "sender")
    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    private User patient;

    @JoinColumn(name = "recipient")
    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    private User veterinarian;


    public void addPatient(User sender) {
        this.setPatient(sender);
        if (sender.getAppointments() == null) {
            sender.setAppointments(new ArrayList<>());
        }
        sender.getAppointments().add(this);
    }

    public void addVeterinarian(User recipient) {
        this.setVeterinarian(recipient);
        if (recipient.getAppointments() == null) {
            recipient.setAppointments(new ArrayList<>());
        }
        recipient.getAppointments().add(this);
    }

    public void setAppointmentNo() {
        this.appointmentNo = String.valueOf(new Random().nextLong()).substring(1,11);
    }
    

}
