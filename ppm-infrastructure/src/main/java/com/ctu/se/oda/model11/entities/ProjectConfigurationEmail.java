package com.ctu.se.oda.model11.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "project_configuration_email")
@Data
@NoArgsConstructor
public class ProjectConfigurationEmail implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Email email;

    @ManyToOne
    private ProjectConfiguration projectConfiguration;


}
