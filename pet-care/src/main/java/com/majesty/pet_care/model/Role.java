package com.majesty.pet_care.model;

import java.util.Collection;
import java.util.HashSet;
import org.hibernate.annotations.NaturalId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name != null ? name : "";
    }

    @Override
    public String toString() {
        return name;
    }

}
