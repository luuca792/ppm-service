package com.ctu.se.oda.model11.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "emails")
@Data
@NoArgsConstructor
public class Email implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "email")
    List<ProjectConfigurationEmail> projectConfigurationEmails = new ArrayList<>();

    public Email(String email) {
        this.email = email;
    }

    public Email(UUID id, String email) {
        this.id = id;
        this.email = email;
    }
}
