package com.ctu.se.oda.model11.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
public class Project implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "creator_id")
    private UUID creatorId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_configuration_id", referencedColumnName = "id")
    private ProjectConfiguration projectConfiguration;

    public Project(String name, Double duration, UUID creatorId) {
        this.name = name;
        this.duration = duration;
        this.creatorId = creatorId;
    }

    public Project(UUID id, String name, Double duration, UUID creatorId) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.creatorId = creatorId;
    }
}
