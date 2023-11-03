package com.ctu.se.oda.model11.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

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

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "email")
    List<ProjectConfigurationEmail> projectConfigurationEmails = new ArrayList<>();

    @JsonCreator
    public Email(@JsonProperty("email_address") String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Email(UUID id, String emailAddress) {
        this.id = id;
        this.emailAddress = emailAddress;
    }
}
